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

package bz.davide.dmweb.shared.view;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;

/**
 * @author Davide Montesin <d@vide.bz>
 */
public class DMHashNavigationPanelAttachHandler implements AttachListener
{
   DMHashNavigationPanel navigationPanel;

   public DMHashNavigationPanelAttachHandler(DMHashNavigationPanel navigationPanel)
   {
      super();
      this.navigationPanel = navigationPanel;
   }

   protected DMHashNavigationPanelAttachHandler()
   {

   }

   @Override
   public void onAttachOrDetach(AttachEvent event)
   {
      History.addValueChangeHandler(new ValueChangeHandler<String>()
      {
         @Override
         public void onValueChange(ValueChangeEvent<String> event)
         {
            String pageIndex = event.getValue();
            if (pageIndex == null || pageIndex.length() == 0)
            {
               pageIndex = "0";
            }
            DMHashNavigationPanelAttachHandler.this.navigationPanel.navigate(Integer.parseInt(pageIndex));
         }
      });

   }
}
