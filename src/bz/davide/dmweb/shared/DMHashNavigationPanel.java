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

import com.google.gwt.user.client.History;

/**
 * @author Davide Montesin <d@vide.bz>
 */
public class DMHashNavigationPanel extends DMFlowPanel
{
   ArrayList<DMWidget>          pages          = new ArrayList<DMWidget>();
   ArrayList<PageChangeHandler> changeHandlers = new ArrayList<PageChangeHandler>();
   int                          index          = -1;

   public DMHashNavigationPanel(String styleName, DMWidget initialContent)
   {
      super(styleName);
      this.addAttachHandler(new DMHashNavigationPanelAttachHandler(this));
      this.newPage(initialContent, false);
   }

   protected DMHashNavigationPanel(Void void1)
   {
      super(void1);
   }

   public void navigate(int toIndex)
   {
      if (toIndex == this.index)
      {
         return;
      }
      DMWidget currPage = this.pages.get(this.index);
      PageChangeHandler tmpChangeHandler = this.changeHandlers.get(this.index);
      if (tmpChangeHandler != null)
      {
         tmpChangeHandler.pageHide();
      }
      this.remove(currPage);
      DMWidget newPage = this.pages.get(toIndex);
      tmpChangeHandler = this.changeHandlers.get(toIndex);
      this.add(newPage);
      if (tmpChangeHandler != null)
      {
         tmpChangeHandler.pageShow();
      }
      this.index = toIndex;
   }

   public void newPage(DMWidget widget)
   {
      this.newPage(widget, true);
   }

   void newPage(DMWidget widget, boolean history)
   {
      PageChangeHandler changeHandler = null;
      if (widget instanceof PageChangeHandler)
      {
         changeHandler = (PageChangeHandler) widget;
      }
      this.newPage(widget, changeHandler, history);
   }

   public void newPage(DMWidget widget, PageChangeHandler changeHandler)
   {
      this.newPage(widget, changeHandler, true);
   }

   void newPage(DMWidget widget, PageChangeHandler changeHandler, boolean history)
   {
      // Remove all the pages after the current index
      while (this.pages.size() - 1 > this.index)
      {
         int idx = this.pages.size() - 1;
         this.pages.remove(idx);
      }
      if (this.pages.size() > 0)
      {
         int idx = this.pages.size() - 1;
         DMWidget currPage = this.pages.get(idx);
         this.remove(currPage);
         PageChangeHandler tmpChangeHandler = this.changeHandlers.get(idx);
         if (tmpChangeHandler != null)
         {
            tmpChangeHandler.pageHide();
         }
      }
      this.pages.add(widget);
      this.changeHandlers.add(changeHandler);
      this.add(widget);
      if (changeHandler != null)
      {
         changeHandler.pageShow();
      }
      this.index++;

      if (history)
      {
         History.newItem(String.valueOf(this.index), false);
      }

   }

}