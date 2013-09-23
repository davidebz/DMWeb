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
import com.google.gwt.user.client.Element;

/**
 * @author Davide Montesin <d@vide.bz>
 */
public class Map
{
   JavaScriptObject jsMap;

   public Map(Element element)
   {
      this.jsMap = newMap(element);
   }

   private static native JavaScriptObject newMap(Element element)/*-{
		var map = new $wnd.L.Map(element, {
			zoomControl : false
		});
		var control = new $wnd.L.Control.Zoom({
			position : 'bottomleft'
		});
		map.addControl(control);
		var scale = new $wnd.L.Control.Scale({
			position : 'topleft',
			imperial : false
		});
		map.addControl(scale);
		return map;
   }-*/;

   public native void addLayer(Layer layer)/*-{
		this.@bz.davide.dmweb.client.leaflet.Map::jsMap
				.addLayer(layer.@bz.davide.dmweb.client.leaflet.Layer::jsLayer);
   }-*/;

   public native void removeLayer(Layer layer)/*-{
		this.@bz.davide.dmweb.client.leaflet.Map::jsMap
				.removeLayer(layer.@bz.davide.dmweb.client.leaflet.Layer::jsLayer);
   }-*/;

   public native void setView(LatLng latLng, int zoom)/*-{
		this.@bz.davide.dmweb.client.leaflet.Map::jsMap.setView(
				latLng.@bz.davide.dmweb.client.leaflet.LatLng::jsLatLng, zoom);
   }-*/;

   public native int getZoom()/*-{
		return this.@bz.davide.dmweb.client.leaflet.Map::jsMap.getZoom();
   }-*/;

   public native void fitBounds(LatLngBounds bounds)/*-{
		this.@bz.davide.dmweb.client.leaflet.Map::jsMap
				.fitBounds(bounds.@bz.davide.dmweb.client.leaflet.LatLngBounds::jsBounds);
   }-*/;

   public native void addZoomEndEventListener(EventListener eventListener)/*-{
		this.@bz.davide.dmweb.client.leaflet.Map::jsMap
				.addEventListener(
						'zoomend',
						function(e) {
							eventListener.@bz.davide.dmweb.client.leaflet.EventListener::onEvent()();
						});
   }-*/;

   public native void addDragEndEventListener(EventListener eventListener)/*-{
		this.@bz.davide.dmweb.client.leaflet.Map::jsMap
				.addEventListener(
						'dragend',
						function(e) {
							eventListener.@bz.davide.dmweb.client.leaflet.EventListener::onEvent()();
						});
   }-*/;

   public native void addDragEventListener(EventListener eventListener)/*-{
		this.@bz.davide.dmweb.client.leaflet.Map::jsMap
				.addEventListener(
						'drag',
						function(e) {
							eventListener.@bz.davide.dmweb.client.leaflet.EventListener::onEvent()();
						});
   }-*/;

   public LatLng getCenter()
   {
      return new LatLng(this.getNativeCenter());
   }

   private native JavaScriptObject getNativeCenter()/*-{
		return this.@bz.davide.dmweb.client.leaflet.Map::jsMap.getCenter();
   }-*/;

   public native void openPopup(Element content, LatLng latLng)/*-{
		this.@bz.davide.dmweb.client.leaflet.Map::jsMap.openPopup(content,
				latLng.@bz.davide.dmweb.client.leaflet.LatLng::jsLatLng);
   }-*/;

   public native void closePopup() /*-{
		this.@bz.davide.dmweb.client.leaflet.Map::jsMap.closePopup();
   }-*/;

}
