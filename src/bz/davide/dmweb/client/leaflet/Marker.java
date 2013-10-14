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

package bz.davide.dmweb.client.leaflet;

import com.google.gwt.core.client.JavaScriptObject;

public class Marker extends Layer
{
   public Marker(LatLng latLng)
   {
      this(latLng, new MarkerOptions());
   }

   public Marker(LatLng latLng, MarkerOptions markerOptions)
   {
      this.jsLayer = newMarker(latLng.jsLatLng, markerOptions.jsMarkerOptions);
   }

   static native JavaScriptObject newMarker(JavaScriptObject latLng, JavaScriptObject options) /*-{
		return new $wnd.L.Marker(latLng, options);
   }-*/;

   public native void addClickEventListener(EventListener eventListener)/*-{
		this.@bz.davide.dmweb.client.leaflet.Layer::jsLayer
				.addEventListener(
						'click',
						function(e) {
							eventListener.@bz.davide.dmweb.client.leaflet.EventListener::onEvent()();
						});
   }-*/;

}
