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
import bz.davide.dmweb.shared.view.DMClickHandler;
import bz.davide.dmweb.shared.view.FormView;
import bz.davide.dmweb.shared.view.InputView;
import bz.davide.dmweb.shared.view.LabelView;
import bz.davide.dmweb.shared.view.SpanView;

public class SignInView extends FormView
{
   InputView user;
   InputView pass;

   public static class InitParameters extends FormView.InitParameters
   {
      DMClickHandler onclick;

      public InitParameters()
      {
         this(null);
      }

      public InitParameters(DMClickHandler onclick)
      {
         super();
         this.onclick = onclick;
      }

   }

   public SignInView(InitParameters initParameters)
   {
      super(initParameters);
      this.setStyleName("form-signin");
      SpanView heading = new SpanView(new SpanView.InitParameters("Please log in"));
      this.appendChild(heading);
      this.user = new InputView(new InputView.InitParameters());
      this.user.setStyleName("form-control");
      this.pass = new InputView(new InputView.InitParameters());
      this.pass.setStyleName("form-control");
      LabelView labelView = new LabelView(new LabelView.InitParameters("Remember me", "checkbox"));
      InputView remember = new InputView(new InputView.InitParameters());
      remember.setType("checkbox");
      labelView.appendChild(remember);
      ButtonView login = new ButtonView(new ButtonView.InitParameters("Login"));
      login.setStyleName("btn btn-lg btn-primary btn-block");
      if (initParameters.onclick != null)
      {
         login.addClickHandler(initParameters.onclick);
      }
      this.appendChild(this.user);
      this.appendChild(this.pass);
      this.appendChild(labelView);
      this.appendChild(login);
   }

   protected SignInView()
   {
   }

   public String getUser()
   {
      return this.user.getValue();
   }

   public String getPassword()
   {
      return this.pass.getValue();
   }

}
