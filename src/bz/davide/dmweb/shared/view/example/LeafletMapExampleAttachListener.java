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

package bz.davide.dmweb.shared.view.example;

import bz.davide.dmweb.client.leaflet.EventListener;
import bz.davide.dmweb.client.leaflet.LatLng;
import bz.davide.dmweb.client.leaflet.Marker;
import bz.davide.dmweb.shared.view.AbstractHtmlElementView;
import bz.davide.dmweb.shared.view.AttachEvent;
import bz.davide.dmweb.shared.view.AttachListener;
import bz.davide.dmweb.shared.view.DivView;
import bz.davide.dmweb.shared.view.LeafletMapView;
import bz.davide.dmweb.shared.view.TextNodeView;

public class LeafletMapExampleAttachListener implements AttachListener
{
   LeafletMapView leafletMap;
   POI[]          pois;

   public LeafletMapExampleAttachListener(LeafletMapView leafletMap, POI[] pois)
   {
      this.leafletMap = leafletMap;
      this.pois = pois;
   }

   public LeafletMapExampleAttachListener()
   {
   }

   @Override
   public void onAttachOrDetach(AttachEvent event)
   {
      if (event.isAttached())
      {
         for (final POI poi : this.pois)
         {
            final LatLng latLng = new LatLng(poi.lat, poi.lon);
            Marker marker = new Marker(latLng);
            marker.addClickEventListener(new EventListener()
            {
               @Override
               public void onEvent()
               {
                  DivView popupContent = new DivView(new DivView.InitParameters("popup-content"));
                  popupContent.appendChild(new TextNodeView(poi.name));
                  LeafletMapExampleAttachListener.this.leafletMap.getLeafletMap().openPopup(popupContent.getElement(),
                                                                                            latLng);
                  AbstractHtmlElementView.notifyAttachRecursive(popupContent);

               }
            });
            this.leafletMap.getLeafletMap().addLayer(marker);
         }
      }

   }
}
