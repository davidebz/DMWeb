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

package bz.davide.dmweb.shared.model;

/**
 * @author Davide Montesin <d@vide.bz>
 */
public class Img extends AbstractHtmlElement
{
   String src;

   public Img()
   {
      super("img");
   }

   protected Img(Void void1)
   {
      super(void1);
   }

   public String getSrc()
   {
      return this.src;
   }
}
