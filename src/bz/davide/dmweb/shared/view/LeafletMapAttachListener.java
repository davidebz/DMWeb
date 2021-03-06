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

package bz.davide.dmweb.shared.view;

import bz.davide.dmweb.client.leaflet.LatLng;
import bz.davide.dmweb.client.leaflet.Map;
import bz.davide.dmweb.client.leaflet.OSMLayer;

/**
 * @author Davide Montesin <d@vide.bz>
 */
public class LeafletMapAttachListener implements AttachListener
{
   LeafletMapView mapWidget;

   public LeafletMapAttachListener(LeafletMapView mapWidget)
   {
      super();
      this.mapWidget = mapWidget;
   }

   protected LeafletMapAttachListener(Void void1)
   {
   }

   protected LeafletMapAttachListener()
   {
   }

   @Override
   public void onAttachOrDetach(AttachEvent event)
   {
      if (event.isAttached())
      {
         this.mapWidget.leafletMap = new Map(this.mapWidget.getElement());
         this.mapWidget.leafletMap.addLayer(new OSMLayer());
         this.mapWidget.leafletMap.setView(new LatLng(this.mapWidget.initParameters.initialLat,
                                                      this.mapWidget.initParameters.initialLon),
                                           this.mapWidget.initParameters.initialZoom);
      }
   };
}
