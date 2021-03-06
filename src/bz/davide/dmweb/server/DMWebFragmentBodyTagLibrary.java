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

import java.util.ArrayList;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import bz.davide.dmweb.shared.view.AbstractHtmlElementView;
import bz.davide.dmweb.shared.view.AttachListener;
import bz.davide.dmweb.shared.view.DMWidgetSerializationData;
import bz.davide.dmxmljson.marshalling.Marshaller;
import bz.davide.dmxmljson.marshalling.json.JSONStructure;
import bz.davide.dmxmljson.unmarshalling.Unmarshaller;

public class DMWebFragmentBodyTagLibrary extends BodyTagSupport
{
   DMWidgetSerializationData serializationData;
   Marshaller                marshaller;
   Unmarshaller              unmarshaller;

   String                    marshallerUnmarshallerPrefix;

   public void setMarshallerUnmarshallerPrefix(String marshallerUnmarshallerPrefix)
   {
      this.marshallerUnmarshallerPrefix = marshallerUnmarshallerPrefix;
   }

   public String getMarshallerUnmarshallerPrefix()
   {
      return this.marshallerUnmarshallerPrefix;
   }

   @Override
   public int doStartTag() throws JspException
   {
      try
      {
         AbstractHtmlElementView.clientSide = false;

         ArrayList<AttachListener> domReady = new ArrayList<AttachListener>();
         this.serializationData = new DMWidgetSerializationData(domReady);

         this.unmarshaller = (Unmarshaller) Class.forName(this.marshallerUnmarshallerPrefix + "Unmarshaller").newInstance();
         this.marshaller = (Marshaller) Class.forName(this.marshallerUnmarshallerPrefix + "Marshaller").newInstance();

         return super.doStartTag();
      }
      catch (Exception exxx)
      {
         throw new JspException(exxx);
      }
   }

   @Override
   public int doEndTag() throws JspException
   {
      try
      {
         BodyContent bc = this.getBodyContent();
         String body = bc.getString();
         JspWriter out = this.pageContext.getOut();
         out.println("<body>");
         out.print(body);

         JSONStructure jsonStructure = new JSONStructure(0);
         this.marshaller.marschall(this.serializationData, jsonStructure);
         String json = jsonStructure.toString();

         out.println("<script>\nvar bz_davide_dm_widgets = " + json + "</script>");
         out.println("</body>");
         return BodyTagSupport.EVAL_PAGE;
      }
      catch (Exception exxx)
      {
         throw new JspException(exxx);
      }
   }
}
