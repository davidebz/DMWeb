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

import java.util.ArrayList;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.user.client.Event;

/**
 * @author Davide Montesin <d@vide.bz>
 */
public class InputView extends AbstractHtmlElementView implements DivViewChild
{

   ArrayList<DMKeyUpHandler> keyUpHandlers;
   ArrayList<DMFocusHandler> focusHandlers;

   public InputView()
   {
      super("input");
      this.keyUpHandlers = new ArrayList<DMKeyUpHandler>();
      this.focusHandlers = new ArrayList<DMFocusHandler>();
   }

   public InputView(String text)
   {
      this();
      this.setText(text);
   }

   public InputView(String text, String styleName)
   {
      this(text);
      this.setStyleName(styleName);
   }

   @Override
   protected void onBrowserEvent(Event event)
   {
      super.onBrowserEvent(event);
      String eventType = event.getType();
      if (eventType.equals("keyup"))
      {
         for (DMKeyUpHandler keyUpHandler : this.keyUpHandlers)
         {
            keyUpHandler.onKeyUp(new DMKeyUpEvent());
         }
      }
      if (eventType.equals("focus"))
      {
         for (DMFocusHandler focusHandler : this.focusHandlers)
         {
            focusHandler.onFocus(new DMFocusEvent(event));
         }
      }
   }

   public void addFocusHandler(DMFocusHandler focusHandler)
   {
      this.focusHandlers.add(focusHandler);
      this.addSinkEvents(Event.FOCUSEVENTS);
   }

   public void addKeyUpHandler(DMKeyUpHandler keyUpHandler)
   {
      this.keyUpHandlers.add(keyUpHandler);
      this.addSinkEvents(Event.ONKEYUP);
   }

   public void setText(String value)
   {
      if (AbstractHtmlElementView.clientSide)
      {
         InputElement.as(this.getElement()).setValue(value);
      }
      else
      {
         this.setElementAttribute("value", value);
      }
   }

   public void setType(String type)
   {
      this.setElementAttribute("type", type);
   }

   public String getValue()
   {
      return InputElement.as(this.getElement()).getValue();
   }
}
