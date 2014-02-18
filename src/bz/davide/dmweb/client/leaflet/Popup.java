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

package bz.davide.dmweb.client.leaflet;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;

/**
 * @author Davide Montesin <d@vide.bz>
 */
public class Popup extends Layer
{
   public Popup()
   {
      this.jsLayer = newPopup();
   }

   public native void setContent(Element element)/*-{
		this.@bz.davide.dmweb.client.leaflet.Layer::jsLayer.setContent(element);
   }-*/;

   public native void setLatLng(LatLng latLng)/*-{
		this.@bz.davide.dmweb.client.leaflet.Layer::jsLayer
				.setLatLng(latLng.@bz.davide.dmweb.client.leaflet.LatLng::jsLatLng);
   }-*/;

   static native JavaScriptObject newPopup()/*-{
		var popup = new $wnd.L.Popup()
		return popup;
   }-*/;

}
