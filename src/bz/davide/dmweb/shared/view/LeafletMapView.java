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

import bz.davide.dmweb.client.leaflet.Map;

/**
 * @author Davide Montesin <d@vide.bz>
 */
public class LeafletMapView extends DivView
{
   InitParameters initParameters;

   public static class InitParameters extends DivView.InitParameters
   {
      int    width;
      int    height;

      double initialLat;
      double initialLon;
      int    initialZoom;

      public InitParameters()
      {
      }

      public InitParameters(int width, int height)
      {
         this.width = width;
         this.height = height;
      }

      public int getWidth()
      {
         return this.width;
      }

      public int getHeight()
      {
         return this.height;
      }
   }

   transient Map leafletMap = null;

   public LeafletMapView(InitParameters initParameters)
   {
      super(initParameters);
      this.initParameters = initParameters;
      this.setStyleName("leaflet-map-widget");
      if (initParameters.width != 0 && initParameters.height != 0)
      {
         this.setElementAttribute("style", "width:"
                                           + initParameters.width
                                           + "px;height:"
                                           + initParameters.height
                                           + "px");
      }
      this.addAttachHandler(new LeafletMapAttachListener(this));
   }

   protected LeafletMapView()
   {
   }

   public Map getLeafletMap()
   {
      return this.leafletMap;
   }
}
