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

/**
 * @author Davide Montesin <d@vide.bz>
 */
public class OSMLayer extends Layer
{
   public OSMLayer()
   {
      this("http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");
   }

   public OSMLayer(String tilesUrl)
   {
      this.jsLayer = newOSMLayer(4, 18, tilesUrl);
   }

   static native JavaScriptObject newOSMLayer(int minZoom, int maxZoom, String tilesUrl)/*-{
		var osmUrl = tilesUrl;
		var osmAttrib = 'Map data © OpenStreetMap contributors';
		var osm = new $wnd.L.TileLayer(osmUrl, {
			minZoom : minZoom,
			maxZoom : maxZoom,
			attribution : osmAttrib
		});
		return osm;
   }-*/;

}
