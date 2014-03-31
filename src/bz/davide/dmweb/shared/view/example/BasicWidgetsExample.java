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

import bz.davide.dmweb.shared.view.ButtonView;
import bz.davide.dmweb.shared.view.DivView;
import bz.davide.dmweb.shared.view.SpanView;

public class BasicWidgetsExample extends DivView
{
   public static class InitParameters extends DivView.InitParameters
   {
   }

   public BasicWidgetsExample(InitParameters initParameters)
   {
      super(initParameters);
      this.setStyleName("basic-widgets-example");
      ButtonView button = new ButtonView(new ButtonView.InitParameters("A button"));
      this.appendChild(button);
      SpanView spanView = new SpanView(new SpanView.InitParameters("This is a text"));
      this.appendChild(spanView);

      DivView buttonGroup = new DivView(new DivView.InitParameters("btn-group"));
      for (int i = 0; i < 10; i++)
      {
         button = new ButtonView(new ButtonView.InitParameters("A button"));
         button.setStyleName("btn btn-default");
         buttonGroup.appendChild(button);
      }
      this.appendChild(buttonGroup);
      this.appendChild(new SignInView(new SignInView.InitParameters()));

   }

   BasicWidgetsExample()
   {
   }
}
