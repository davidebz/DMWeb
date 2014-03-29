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

import bz.davide.dmweb.shared.view.DivView;
import bz.davide.dmweb.shared.view.LeafletMapView;
import bz.davide.dmweb.shared.view.SpanView;

public class LeafletMapExample extends DivView
{

   public static class InitParameters extends DivView.InitParameters
   {

      String                        title;
      LeafletMapView.InitParameters map;
      POI[]                         pois;
   }

   public LeafletMapExample(InitParameters initParameters)
   {
      super(initParameters);
      this.setStyleName("leaflet-map-example");
      if (initParameters.map.getWidth() != 0)
      {
         this.setElementAttribute("style", "width:" + initParameters.map.getWidth() + "px;");
      }
      LeafletMapView leafletMapView = new LeafletMapView(initParameters.map);
      String title = "";
      if (initParameters.title != null)
      {
         title = initParameters.title;
      }
      this.appendChild(new SpanView(new SpanView.InitParameters(title, "title")));
      this.appendChild(leafletMapView);

      this.addAttachHandler(new LeafletMapExampleAttachListener(leafletMapView, initParameters.pois));

   }

   protected LeafletMapExample()
   {
   }

}
