/*
DMWeb - Java web framework - http://www.davide.bz/dmweb

Copyright (C) 2013-2014 Davide Montesin <d@vide.bz> - Bolzano/Bozen - Italy

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

import java.io.ByteArrayInputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import bz.davide.dmweb.shared.view.AbstractHtmlElementView;
import bz.davide.dmxmljson.unmarshalling.xml.W3CXMLStructure;

public class DMWebFragmentTagLibrary extends BodyTagSupport
{

   String viewclass;

   String bodyContainsParameters;

   public void setViewclass(String viewclass)
   {
      this.viewclass = viewclass;
   }

   public String getViewclass()
   {
      return this.viewclass;
   }

   public void setBodyContainsParameters(String bodyContainsParameters)
   {
      this.bodyContainsParameters = bodyContainsParameters;
   }

   public String getBodyContainsParameters()
   {
      return this.bodyContainsParameters;
   }

   @Override
   public int doEndTag() throws JspException
   {
      try
      {
         DMWebFragmentBodyTagLibrary container = (DMWebFragmentBodyTagLibrary) BodyTagSupport.findAncestorWithClass(this,
                                                                                                                    DMWebFragmentBodyTagLibrary.class);

         Class clazz = Class.forName(this.viewclass);

         AbstractHtmlElementView[] views;

         BodyContent bc = this.getBodyContent();
         if (bc != null)
         {
            String body = bc.getString();

            Constructor initConstructor = null;
            for (Constructor constructor : clazz.getConstructors())
            {
               if (constructor.getParameterTypes().length == 1)
               {
                  initConstructor = constructor;
               }
            }

            Object initParameters = initConstructor.getParameterTypes()[0].newInstance();

            W3CXMLStructure w3cxmlStructure = new W3CXMLStructure(new ByteArrayInputStream(body.getBytes("UTF-8")));
            container.unmarshaller.unmarschall(w3cxmlStructure, initParameters);

            Object viewObject = initConstructor.newInstance(initParameters);

            if (viewObject instanceof ArrayList)
            {
               views = ((ArrayList<AbstractHtmlElementView>) viewObject).toArray(new AbstractHtmlElementView[0]);
            }
            else
            {
               views = new AbstractHtmlElementView[] { (AbstractHtmlElementView) viewObject };
            }

         }
         else
         {
            views = new AbstractHtmlElementView[] { (AbstractHtmlElementView) clazz.newInstance() };
         }

         StringBuffer generatedHtml = new StringBuffer();

         for (AbstractHtmlElementView view : views)
         {
            AbstractHtmlElementView.generateServerSideHtml(container.serializationData, view, generatedHtml);
         }

         this.pageContext.getOut().print(generatedHtml.toString());

         return BodyTagSupport.EVAL_PAGE;
      }
      catch (Exception exxx)
      {
         throw new JspException(exxx);
      }
   }
}
