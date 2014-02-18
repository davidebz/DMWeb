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

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

/**
 * @author Davide Montesin <d@vide.bz>
 */
public class DMDateBoxFactory implements DMGwtWidgetHostFactory
{
   DMDateBox dmDateBox;

   public DMDateBoxFactory(DMDateBox dmDateBox)
   {
      super();
      this.dmDateBox = dmDateBox;
   }

   @Override
   public Widget create()
   {
      DatePicker datePicker = new DatePicker();
      DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat(this.dmDateBox.format);
      DateBox dateBox = new DateBox(datePicker,
                                    new Date(this.dmDateBox.timestamp),
                                    new DateBox.DefaultFormat(dateTimeFormat));
      this.dmDateBox.dateBox = dateBox;
      this.dmDateBox.timestamp = 0;
      return dateBox;
   }

}
