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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Davide Montesin <d@vide.bz>
 */
public class RedirectPermanentFilter implements Filter
{

   ArrayList<Pattern> patterns;
   ArrayList<String>  redirects;

   @Override
   public void init(FilterConfig config) throws ServletException
   {
      this.patterns = new ArrayList<Pattern>();
      this.redirects = new ArrayList<String>();

      @SuppressWarnings("unchecked")
      Enumeration<String> parameterNames = config.getInitParameterNames();
      while (parameterNames.hasMoreElements())
      {
         String name = parameterNames.nextElement();
         String value = config.getInitParameter(name);
         String[] parts = value.split(":");
         String re = parts[0];
         String redirect = parts[1];
         this.patterns.add(Pattern.compile(re));
         this.redirects.add(redirect);
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

         for (int i = 0; i < this.patterns.size(); i++)
         {
            Pattern pattern = this.patterns.get(i);
            String redirect = this.redirects.get(i);
            if (pattern.matcher(requestPath).matches())
            {
               httpResponse.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
               httpResponse.setHeader("Location", redirect);
               return;
            }
         }

      }
      chain.doFilter(request, response);
   }

   @Override
   public void destroy()
   {

   }
}
