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
public class Polyline extends Path
{
   public Polyline(LatLng[] vertex)
   {
      this(vertex, new PathOptions());
   }

   public Polyline(LatLng[] vertex, PathOptions polylineOptions)
   {
      JavaScriptObject jsArray = Polygon.jsArray();
      for (LatLng latLng : vertex)
      {
         Polygon.addItem(jsArray, latLng.jsLatLng);
      }
      this.jsLayer = newPolyline(jsArray, polylineOptions.jsOptions);
   }

   static native JavaScriptObject newPolyline(JavaScriptObject vertex, JavaScriptObject options)/*-{
		return new $wnd.L.Polyline(vertex, options);
   }-*/;

}
