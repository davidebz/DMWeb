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

package bz.davide.dmweb.shared.model;

import java.util.ArrayList;

/**
 * @author Davide Montesin <d@vide.bz>
 */
public class AbstractHtmlElement implements Node
{
   String                    tagName;

   protected ArrayList<Node> childNodes = new ArrayList<Node>();

   public AbstractHtmlElement(String tagName)
   {
      this.tagName = tagName;
   }

   protected AbstractHtmlElement(Void void1)
   {
   }

   public Node[] getChildNodes()
   {
      return this.childNodes.toArray(new Node[0]);
   }

}
