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
import bz.davide.dmweb.shared.view.FormView;
import bz.davide.dmweb.shared.view.InputView;
import bz.davide.dmweb.shared.view.LabelView;
import bz.davide.dmweb.shared.view.SpanView;

public class ComplexForm extends DivView
{
   public static class InitParameters extends DivView.InitParameters
   {

   }

   public ComplexForm(InitParameters initParameters)
   {
      super(initParameters);
      this.setStyleName(".container");
      FormView formView = new FormView(new FormView.InitParameters());
      DivView formGroup1 = new DivView(new DivView.InitParameters("form-group has-success has-feedback"));
      formGroup1.appendChild(new LabelView(new LabelView.InitParameters("Input name", "control-label")));
      formGroup1.appendChild(new InputView(new InputView.InitParameters("Davide", "form-control")));
      formGroup1.appendChild(new SpanView(new SpanView.InitParameters("",
                                                                      "glyphicon glyphicon-ok form-control-feedback")));
      formView.appendChild(formGroup1);
      this.appendChild(formView);
   }

   protected ComplexForm()
   {
   }
}
