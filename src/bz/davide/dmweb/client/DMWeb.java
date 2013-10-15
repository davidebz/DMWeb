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

package bz.davide.dmweb.client;

import bz.davide.dmweb.shared.i18n.I18N;
import bz.davide.dmweb.shared.view.AbstractHtmlElementView;
import bz.davide.dmweb.shared.view.AttachEvent;
import bz.davide.dmweb.shared.view.AttachListener;
import bz.davide.dmweb.shared.view.DMWidgetSerializationData;
import bz.davide.dmxmljson.unmarshalling.Unmarshaller;
import bz.davide.dmxmljson.unmarshalling.json.gwt.GWTStructure;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.Window;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * @author Davide Montesin <d@vide.bz>
 */
public class DMWeb implements EntryPoint
{

   /**
    * This is the entry point method.
    */
   @Override
   public void onModuleLoad()
   {
   }

   public static void start(Unmarshaller widgetUnmarshaller)
   {
      try
      {
         JSONObject jsonObject = new JSONObject(readSerializationData());
         GWTStructure gwtStructure = new GWTStructure(jsonObject);

         DMWidgetSerializationData serializationData = (DMWidgetSerializationData) widgetUnmarshaller.newInstance("DMWidgetSerializationData");
         widgetUnmarshaller.unmarschall(gwtStructure, serializationData);
         AbstractHtmlElementView.clientSideIdSeq = serializationData.getIdseq();
         I18N.singleton = serializationData.getI18n();

         for (AttachListener attachHandler : serializationData.getDomReady())
         {
            attachHandler.onAttachOrDetach(new AttachEvent(true));
         }
         for (AttachListener attachHandler : serializationData.getAttachHandlers())
         {
            attachHandler.onAttachOrDetach(new AttachEvent(true));
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         Window.alert("Errr");
      }
   }

   static native JavaScriptObject readSerializationData()/*-{
		return $wnd.bz_davide_dm_widgets;
   }-*/;

}
