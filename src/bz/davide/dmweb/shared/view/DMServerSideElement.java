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

package bz.davide.dmweb.shared.view;

import java.util.HashMap;

/**
 * @author Davide Montesin <d@vide.bz>
 */
public class DMServerSideElement
{
   String                  tagName;

   HashMap<String, String> attributes = null;
   HashMap<String, Void>   styles     = null;
   String                  innerHtml  = null;

   public DMServerSideElement(String tagName)
   {
      super();
      this.tagName = tagName;
      this.attributes = new HashMap<String, String>();
      this.styles = new HashMap<String, Void>();
   }

   public void setClassName(String name)
   {
      this.styles.clear();
      this.styles.put(name, null);
   }

   public void addClassName(String name)
   {
      this.styles.put(name, null);
   }

   public void removeClassName(String name)
   {
      this.styles.remove(name);
   }

}
