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

package bz.davide.dmweb.client.google.visualization;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;

/**
 * @author Davide Montesin <d@vide.bz>
 */
public class Gauge
{
   JavaScriptObject jsGauge;

   public Gauge(Element element, String title, int min_, int value, int max_)
   {
      this.jsGauge = newGauge(element, title, min_, value, max_);
   }

   static native JavaScriptObject newGauge(Element element, String title, int min_, int value, int max_)/*-{

		var data = $wnd.google.visualization.arrayToDataTable([
				[ 'Label', 'Value' ], [ title, value ] ]);

		var options = {
			width : 200,
			height : 200,
			redFrom : max_ * .9,
			redTo : max_,
			yellowFrom : max_ * .75,
			yellowTo : max_ * .90,
			minorTicks : 5,
			min : min_,
			max : max_

		};

		var chart = new $wnd.google.visualization.Gauge(element);
		chart.draw(data, options);

		return chart;

   }-*/;

}
