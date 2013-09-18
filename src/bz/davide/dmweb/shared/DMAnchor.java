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

package bz.davide.dmweb.shared;

/**
 * @author Davide Montesin <d@vide.bz>
 */
public class DMAnchor extends DMWidget
{
   public DMAnchor(String href, String text, String target)
   {
      super("a");
      this.setHref(href);
      this.setText(text);
      if (target != null)
      {
         this.setTarget(target);
      }
   }

   public DMAnchor(String href, String text)
   {
      this(href, text, null);
   }

   public DMAnchor()
   {
      this("#", "-Link-");
   }

   protected DMAnchor(Void void1)
   {
      super(void1);
   }

   public void setHref(String href)
   {
      this.setElementAttribute("href", href);
   }

   public void setText(String text)
   {
      this.setElementInnerText(text);
   }

   public void setTarget(String target)
   {
      this.setElementAttribute("target", target);
   }
}
