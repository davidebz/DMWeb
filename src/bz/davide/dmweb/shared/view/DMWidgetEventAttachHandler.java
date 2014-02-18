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

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;

/**
 * @author Davide Montesin <d@vide.bz>
 */
public final class DMWidgetEventAttachHandler implements AttachListener
{
   AbstractHtmlElementView widget;

   public DMWidgetEventAttachHandler(AbstractHtmlElementView widget)
   {
      super();
      this.widget = widget;
   }

   DMWidgetEventAttachHandler()
   {
   }

   @Override
   public void onAttachOrDetach(AttachEvent event)
   {
      if (event.isAttached())
      {
         DOM.sinkEvents(this.widget.getElement(), this.widget.eventBits);
         DOM.setEventListener(this.widget.getElement(), new EventListener()
         {
            @Override
            public void onBrowserEvent(Event event)
            {
               DMWidgetEventAttachHandler.this.widget.onBrowserEvent(event);
            }
         });
      }
      else
      {
         DOM.setEventListener(this.widget.getElement(), null);
      }
   };

}
