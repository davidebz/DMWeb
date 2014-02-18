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

/**
 * @author Davide Montesin <d@vide.bz>
 */
public class ButtonView extends AbstractHtmlElementView implements DivViewChild
{
   public static class InitParameters extends AbstractHtmlElementView.InitParameters
   {
      private String label;

      public InitParameters()
      {
         this(null);
      }

      public InitParameters(String label)
      {
         super("button");
         this.label = label;
      }

   }

   public ButtonView(InitParameters initParameters)
   {
      super(initParameters);
      if (initParameters.label != null)
      {
         this.setLabel(initParameters.label);
      }
   }

   protected ButtonView()
   {
   }

   public void setLabel(String text)
   {
      this.clear();
      this.appendChild(new TextNodeView(text));
   }

   public void appendChild(ButtonViewChild node)
   {
      super.appendChildInternal(node);
   }

}
