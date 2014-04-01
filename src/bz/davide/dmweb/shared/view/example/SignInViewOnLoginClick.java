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

import bz.davide.dmweb.shared.view.DMClickEvent;
import bz.davide.dmweb.shared.view.DMClickHandler;
import bz.davide.dmweb.shared.view.DivView;
import com.google.gwt.user.client.Window;

public class SignInViewOnLoginClick implements DMClickHandler
{

   DivView    mainDiv;
   SignInView signInView;

   public SignInViewOnLoginClick(DivView mainDiv)
   {
      super();
      this.mainDiv = mainDiv;
   }

   SignInViewOnLoginClick()
   {
   }

   public void setSignInView(SignInView signInView)
   {
      this.signInView = signInView;
   }

   @Override
   public void onClick(DMClickEvent event)
   {
      event.preventDefault();
      event.stopPropagation();
      Window.alert("Logged with: " + this.signInView.getUser() + ":" + this.signInView.getPassword());
      this.mainDiv.clear();

   }
}
