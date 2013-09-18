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

package bz.davide.dmweb.client.openlayer;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author Davide Montesin <d@vide.bz>
 */
public class VectorLayer extends Layer
{
   Map map;

   public VectorLayer(String name)
   {
      this.jsLayer = newVectorLayer(name);
   }

   public native void addFeature(VectorFeature feature)/*-{
		this.@bz.davide.dmweb.client.openlayer.Layer::jsLayer
				.addFeatures(feature.@bz.davide.dmweb.client.openlayer.VectorFeature::jsFeature);
   }-*/;

   static native JavaScriptObject newVectorLayer(String name)/*-{
		return new $wnd.OpenLayers.Layer.Vector(name);
   }-*/;

   public native void setSelectFeatureListener(SelectFeatureListener selectListener)/*-{

		var vectorLayer = this.@bz.davide.dmweb.client.openlayer.Layer::jsLayer;

		vectorLayer.events
				.on({
					'featureselected' : function(feature) {
						selectListener.@bz.davide.dmweb.client.openlayer.SelectFeatureListener::onSelect(Ljava/lang/String;)(feature.feature.id);
					},
					'featureunselected' : function(feature) {
					}
				});
		var sf = new $wnd.OpenLayers.Control.SelectFeature(vectorLayer, {
			clickout : true,
			toggle : false,
			multiple : false,
			hover : false,
			toggleKey : "ctrlKey", // ctrl key removes from selection
			multipleKey : "shiftKey", // shift key adds to selection
			box : false
		})
		var map = this.@bz.davide.dmweb.client.openlayer.VectorLayer::map.@bz.davide.dmweb.client.openlayer.Map::jsMap;
		map.addControl(sf);
		sf.activate();
   }-*/;

}
