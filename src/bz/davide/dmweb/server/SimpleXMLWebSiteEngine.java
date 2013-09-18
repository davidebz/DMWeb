/*
DMWeb - Java web framework - http://www.davide.bz/dmweb

Copyright (C) 2013 Davide Montesin <d@vide.bz> - Bolzano/Bozen - Italy

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>
*/

package bz.davide.dmweb.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bz.davide.dmweb.shared.DMWebPage;
import bz.davide.dmweb.shared.DMWidget;
import bz.davide.dmweb.shared.DMWidgetSerializationData;
import bz.davide.dmweb.shared.i18n.I18N;
import bz.davide.dmweb.shared.i18n.I18NData;
import bz.davide.dmxmljson.marshalling.Marshaller;
import bz.davide.dmxmljson.marshalling.json.JSONStructure;
import bz.davide.dmxmljson.unmarshalling.Unmarshaller;
import bz.davide.dmxmljson.unmarshalling.xml.W3CXMLStructure;

/**
 * @author Davide Montesin <d@vide.bz>
 */
public class SimpleXMLWebSiteEngine implements Filter
{
   Unmarshaller webPageReader;
   String       head;
   String       body;
   Marshaller   widgetMarshaller;

   String       homeName = "home";

   @Override
   public void init(FilterConfig initConfig) throws ServletException
   {
      try
      {
         String className = initConfig.getInitParameter("WebPageReaderClassName");
         @SuppressWarnings("rawtypes")
         Class clazz = Class.forName(className);
         this.webPageReader = (Unmarshaller) clazz.newInstance();

         className = initConfig.getInitParameter("WidgetMarshallerClassName");
         clazz = Class.forName(className);
         this.widgetMarshaller = (Marshaller) clazz.newInstance();

         this.head = initConfig.getInitParameter("html-head");
         this.body = initConfig.getInitParameter("html-body");

         DMWidget.clientSide = false;
         I18N.singleton = new I18NServerSide();

      }
      catch (Exception exxx)
      {
         throw new ServletException(exxx);
      }
   }

   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
                                                                                            ServletException
   {
      if (request instanceof HttpServletRequest)
      {
         HttpServletRequest httpRequest = (HttpServletRequest) request;
         HttpServletResponse httpResponse = (HttpServletResponse) response;

         String requestPath = httpRequest.getRequestURI();
         if (!requestPath.startsWith("/"))
         {
            throw new ServletException("Unexpercted missing /");
         }
         requestPath = requestPath.substring(1);

         // XML file dir
         String path = httpRequest.getSession().getServletContext().getRealPath("/WEB-INF/webpages");
         File webpagesDir = new File(path);
         File[] langDirs = webpagesDir.listFiles(new FileFilter()
         {
            @Override
            public boolean accept(File pathname)
            {
               return pathname.isDirectory();
            }
         });

         if (requestPath.length() == 0) // Home page
         {
            String language = "en";
            if (httpRequest.getHeader("Accept-Language") != null)
            {
               @SuppressWarnings("unchecked")
               Enumeration<Locale> locales = httpRequest.getLocales();
               looplocales: while (locales.hasMoreElements())
               {
                  Locale locale = locales.nextElement();
                  for (File langDir : langDirs)
                  {
                     if (langDir.getName().equals(locale.getLanguage()))
                     {
                        language = langDir.getName();
                        break looplocales;
                     }
                  }

               }
            }
            requestPath = language;
         }

         String[] requestFiles = requestPath.split("/");
         if (requestFiles.length == 1)
         {
            httpResponse.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            httpResponse.setHeader("Location", requestFiles[0] + "/" + this.homeName);
            return;
         }

         // Search for the name
         for (File langDir : langDirs)
         {
            if (langDir.getName().equals(requestFiles[0]))
            {
               for (File pageFile : langDir.listFiles())
               {
                  if (pageFile.getName().equals(requestFiles[1] + ".xml"))
                  {
                     try
                     {
                        HashMap<String, String> i18n = this.loadLocalizedTexts(requestFiles[0], webpagesDir);
                        this.processWebPage(httpRequest, httpResponse, pageFile, requestFiles[0], i18n);
                        return;
                     }
                     catch (Exception e)
                     {
                        throw new ServletException(e);
                     }

                  }
               }
            }
         }

      }
      chain.doFilter(request, response);
   }

   HashMap<String, String> loadLocalizedTexts(String lang, File webpagesDir) throws IOException
   {
      HashMap<String, String> result = new HashMap<String, String>();

      File localized = new File(webpagesDir, "i18n_" + lang + ".properties");
      if (localized.exists())
      {
         FileInputStream inputStream = new FileInputStream(localized);
         InputStreamReader reader = new InputStreamReader(inputStream, "utf-8");
         BufferedReader br = new BufferedReader(reader);
         String line;
         try
         {
            while ((line = br.readLine()) != null)
            {
               if (line.startsWith("#"))
               {
                  continue;
               }
               int pos = line.indexOf("=");
               if (pos <= 0)
               {
                  if (line.trim().length() == 0)
                  {
                     continue;
                  }

                  throw new IOException("Invalid line: " + line);

               }
               String key = line.substring(0, pos).trim();
               String text = line.substring(pos + 1).trim();
               result.put(key, text);
            }
         }
         catch (IOException exxx)
         {
            throw exxx;
         }
         finally
         {
            br.close();
            reader.close();
            inputStream.close();
         }

      }

      return result;
   }

   private void processWebPage(HttpServletRequest request,
                               HttpServletResponse response,
                               File f,
                               String language,
                               HashMap<String, String> i18n) throws Exception
   {

      I18NData i18nClientSide = new I18NData(language, i18n);

      ((I18NServerSide) I18N.singleton).dataByThread.set(i18nClientSide);

      FileInputStream fileInputStream = new FileInputStream(f);
      W3CXMLStructure xml = new W3CXMLStructure(fileInputStream);
      String rootObjectName = xml.getRuntimeClassName("DMWebPage");
      DMWebPage webPage = (DMWebPage) this.webPageReader.newInstance(rootObjectName);
      this.webPageReader.unmarschall(xml, webPage);
      fileInputStream.close();

      StringBuffer html = new StringBuffer();
      html.append("<!doctype html>\n");
      html.append("<html>\n");
      html.append("<head>\n");
      html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">\n");
      html.append("<meta name=\"gwt:property\" content=\"locale=" + language + "\">\n");
      html.append("<title>");
      html.append(DMWidget.escapeText4html(webPage.getTitle()));
      html.append("</title>\n");
      html.append(this.head);
      html.append("\n</head>\n");
      html.append("<body>\n");
      html.append(this.body);
      html.append("\n\n");

      DMWidgetSerializationData serializationData = new DMWidgetSerializationData(webPage.getDomReady(),
                                                                                  i18nClientSide);
      for (DMWidget widget : webPage.getBodyContent())
      {
         StringBuffer generatedHtml = new StringBuffer();
         DMWidget.generateServerSideHtml(serializationData, widget, generatedHtml);
         html.append(generatedHtml.toString());
         html.append("\n\n");
      }

      JSONStructure jsonStructure = new JSONStructure();
      this.widgetMarshaller.marschall(serializationData, jsonStructure);
      html.append("<script>\nvar bz_davide_dm_widgets = ");
      html.append(jsonStructure.toString());
      html.append("</script>\n");

      html.append("</body>\n");
      html.append("</html>\n");

      response.setContentType("text/html; charset=utf-8");
      if (request.getParameter("bz.davide.dmweb.noout") != null) // only for stress tests!
      {
         response.getOutputStream().write(("Ok: " + html.toString().length()).toString().getBytes("utf-8"));
      }
      else
      {
         response.getOutputStream().write(html.toString().getBytes("utf-8"));
      }
   }

   @Override
   public void destroy()
   {

   }

}
