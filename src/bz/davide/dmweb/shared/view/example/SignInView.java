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
   InputView  user;
   InputView  pass;
   ButtonView login;

   public SignInView()
   {
      super();
      this.setStyleName("form-signin");
      SpanView heading = new SpanView("Please log in");
      this.appendChild(heading);
      this.user = new InputView();
      this.user.setStyleName("form-control");
      this.pass = new InputView();
      this.pass.setStyleName("form-control");
      LabelView labelView = new LabelView("Remember me", "checkbox");
      InputView remember = new InputView();
      remember.setType("checkbox");
      labelView.appendChild(remember);
      this.login = new ButtonView("Login");
      this.login.setStyleName("btn btn-lg btn-primary btn-block");

      this.appendChild(this.user);
      this.appendChild(this.pass);
      this.appendChild(labelView);
      this.appendChild(this.login);

      this.setLoginOnClick(new SignInViewOnLoginClick(this));
   }

   public void setLoginOnClick(DMClickHandler loginOnClick)
   {
      this.login.addClickHandler(loginOnClick);
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
