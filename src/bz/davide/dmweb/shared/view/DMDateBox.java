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

import java.util.Date;

import com.google.gwt.user.datepicker.client.DateBox;

/**
 * @author Davide Montesin <d@vide.bz>
 */
public class DMDateBox extends DMGwtWidgetHost
{
   transient DateBox dateBox   = null;
   long              timestamp = 0;
   String            format    = "yyyy-MM-dd HH:mm:ss";

   public DMDateBox()
   {
      super();
      this.setStyleName("dm-date-box");
      this.addDMGwtWidgetHostFactory(new DMDateBoxFactory(this));
   }

   public Date getValue()
   {
      if (this.dateBox == null)
      {
         return new Date(this.timestamp);
      }
      else
      {
         return this.dateBox.getValue();
      }
   }

   public void setValue(Date date)
   {
      if (this.dateBox == null)
      {
         this.timestamp = date.getTime();
      }
      else
      {
         this.dateBox.setValue(date);
      }
   }

   public void setFormat(String format)
   {
      this.format = format;
   }

   public DateBox getGwtDateBox()
   {
      return this.dateBox;
   }
}
