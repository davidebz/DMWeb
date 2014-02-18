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

package bz.davide.dmweb.client.openlayer;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;

/**
 * @author Davide Montesin <d@vide.bz>
 */
public class Map
{
   JavaScriptObject jsMap;

   public Map(Element elementId)
   {
      this.jsMap = newMap(elementId);
   }

   public void addLayer(Layer layer)
   {
      if (layer instanceof VectorLayer)
      {
         ((VectorLayer) layer).map = this;
      }
      this._addLayer(layer);
   }

   native void _addLayer(Layer layer)/*-{
		this.@bz.davide.dmweb.client.openlayer.Map::jsMap
				.addLayer(layer.@bz.davide.dmweb.client.openlayer.Layer::jsLayer);
   }-*/;

   public native void zoomToMaxExtent()/*-{
		this.@bz.davide.dmweb.client.openlayer.Map::jsMap.zoomToMaxExtent();
   }-*/;

   public native void addControlPanZoomBar()/*-{
		this.@bz.davide.dmweb.client.openlayer.Map::jsMap
				.addControl(new $wnd.OpenLayers.Control.PanZoomBar());
   }-*/;

   public native void addControlLayerSwitcher()/*-{
		this.@bz.davide.dmweb.client.openlayer.Map::jsMap
				.addControl(new $wnd.OpenLayers.Control.LayerSwitcher());
   }-*/;

   public Projection getProjectionObject()
   {
      return new Projection(this._getProjectionObject());
   }

   native JavaScriptObject _getProjectionObject()/*-{
		return this.@bz.davide.dmweb.client.openlayer.Map::jsMap
				.getProjectionObject()
   }-*/;

   private static native JavaScriptObject newMap(Element elementId)/*-{
		// Without the second parameter, the elementId does not work with DOMElement
		var map = new $wnd.OpenLayers.Map(elementId, {});
		return map;
   }-*/;

   public native void setCenter(LonLat lonLat, int i)/*-{
		this.@bz.davide.dmweb.client.openlayer.Map::jsMap.setCenter(
				lonLat.@bz.davide.dmweb.client.openlayer.LonLat::jsLatLon, i);
   }-*/;

}
