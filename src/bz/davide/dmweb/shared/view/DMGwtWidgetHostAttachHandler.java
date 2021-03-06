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

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Davide Montesin <d@vide.bz>
 */
public final class DMGwtWidgetHostAttachHandler implements AttachListener
{
   AbstractHtmlElementView widget;
   DMGwtWidgetHostFactory  factory;
   transient HTMLPanel     htmlPanel;
   transient Widget        gwtWidget = null;

   public DMGwtWidgetHostAttachHandler(AbstractHtmlElementView widget, DMGwtWidgetHostFactory factory)
   {
      super();
      this.widget = widget;
      this.factory = factory;
   }

   DMGwtWidgetHostAttachHandler()
   {
   }

   @Override
   public void onAttachOrDetach(AttachEvent event)
   {
      if (event.isAttached())
      {
         if (this.gwtWidget == null)
         {
            this.gwtWidget = this.factory.create();
         }
         (this.htmlPanel = HTMLPanel.wrap(this.widget.getElement())).add(this.gwtWidget);
      }
      else
      {
         RootPanel.detachNow(this.htmlPanel);
         this.htmlPanel = null;
      }
   }
}
