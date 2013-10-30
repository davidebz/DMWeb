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

package bz.davide.dmweb.shared.component.example;

import bz.davide.dmweb.shared.view.ButtonView;
import bz.davide.dmweb.shared.view.DivView;
import bz.davide.dmweb.shared.view.InputView;

/**
 * @author Davide Montesin <d@vide.bz>
 */
public class Calculator extends DivView
{
   InputView inputView;

   public Calculator()
   {
      this.inputView = new InputView("");
      this.appendChild(this.inputView);
      DivView buttons = new DivView("buttons");
      this.appendChild(buttons);
      DivView row1 = new DivView("row");
      buttons.appendChild(row1);
      this.createNumberButton(1, row1);
      this.createNumberButton(2, row1);
      this.createNumberButton(3, row1);

      DivView row2 = new DivView("row");
      buttons.appendChild(row2);
      this.createNumberButton(4, row2);
      this.createNumberButton(5, row2);
      this.createNumberButton(6, row2);

      DivView row3 = new DivView("row");
      buttons.appendChild(row3);
      this.createNumberButton(7, row3);
      this.createNumberButton(8, row3);
      this.createNumberButton(9, row3);

   }

   private void createNumberButton(int num, DivView container)
   {
      ButtonView b = new ButtonView(String.valueOf(num));
      container.appendChild(b);
      b.addClickHandler(new NumberButtonClick(num, this));
   }

   protected Calculator(Void void1)
   {
      super(void1);
   }
}
