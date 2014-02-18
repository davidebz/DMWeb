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

/**
 * @author Davide Montesin <d@vide.bz>
 */
public class LonLat
{
   JavaScriptObject jsLatLon;

   public LonLat(double lon, double lat)
   {
      this.jsLatLon = newLonLat(lon, lat);
   }

   public native void transform(Projection source, Projection dest)/*-{
		this.@bz.davide.dmweb.client.openlayer.LonLat::jsLatLon
				.transform(
						source.@bz.davide.dmweb.client.openlayer.Projection::jsProjection,
						dest.@bz.davide.dmweb.client.openlayer.Projection::jsProjection);
   }-*/;

   static native JavaScriptObject newLonLat(double lon, double lat)/*-{
		return new $wnd.OpenLayers.LonLat(lon, lat);
   }-*/;

}
