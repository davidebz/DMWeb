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

import java.util.ArrayList;

import bz.davide.dmweb.shared.i18n.I18NData;

/**
 * @author Davide Montesin <d@vide.bz>
 */
public class DMWidgetSerializationData
{
   ArrayList<DMAttachHandler> domReady;
   ArrayList<DMAttachHandler> attachHandlers = new ArrayList<DMAttachHandler>();
   int                        idseq          = 0;

   I18NData                   i18n           = null;

   public DMWidgetSerializationData(ArrayList<DMAttachHandler> domReady, I18NData i18n)
   {
      this.domReady = domReady;
      this.i18n = i18n;
   }

   DMWidgetSerializationData(Void void1)
   {
   }

   public I18NData getI18n()
   {
      return this.i18n;
   }

   public int getIdseq()
   {
      return this.idseq;
   }

   public ArrayList<DMAttachHandler> getAttachHandlers()
   {
      return this.attachHandlers;
   }

   public ArrayList<DMAttachHandler> getDomReady()
   {
      return this.domReady;
   }
}
