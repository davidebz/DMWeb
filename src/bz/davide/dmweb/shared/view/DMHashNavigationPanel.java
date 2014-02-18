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

import java.util.ArrayList;

import com.google.gwt.user.client.History;

/**
 * @author Davide Montesin <d@vide.bz>
 */
public class DMHashNavigationPanel extends DivView
{
   ArrayList<DivViewChildElement> pages;
   ArrayList<PageChangeHandler>   changeHandlers;
   int                            index;

   public static class InitParameters extends DivView.InitParameters
   {
      DivViewChildElement initialContent;

      public InitParameters(String styleName, DivViewChildElement initialContent)
      {
         super(styleName);
         this.initialContent = initialContent;
      }

   }

   public DMHashNavigationPanel(InitParameters initParameters)
   {
      super(initParameters);

      this.pages = new ArrayList<DivViewChildElement>();
      this.changeHandlers = new ArrayList<PageChangeHandler>();
      this.index = -1;
      this.addAttachHandler(new DMHashNavigationPanelAttachHandler(this));
      this.newPage(initParameters.initialContent, false);
   }

   protected DMHashNavigationPanel()
   {
   }

   public void navigate(int toIndex)
   {
      if (toIndex == this.index)
      {
         return;
      }
      DivViewChildElement currPage = this.pages.get(this.index);
      PageChangeHandler tmpChangeHandler = this.changeHandlers.get(this.index);
      if (tmpChangeHandler != null)
      {
         tmpChangeHandler.pageHide();
      }
      this.remove((AbstractHtmlElementView) currPage);
      DivViewChild newPage = this.pages.get(toIndex);
      tmpChangeHandler = this.changeHandlers.get(toIndex);
      this.appendChild(newPage);
      if (tmpChangeHandler != null)
      {
         tmpChangeHandler.pageShow();
      }
      this.index = toIndex;
   }

   public void newPage(DivViewChildElement widget)
   {
      this.newPage(widget, true);
   }

   void newPage(DivViewChildElement widget, boolean history)
   {
      PageChangeHandler changeHandler = null;
      if (widget instanceof PageChangeHandler)
      {
         changeHandler = (PageChangeHandler) widget;
      }
      this.newPage(widget, changeHandler, history);
   }

   public void newPage(DivViewChildElement widget, PageChangeHandler changeHandler)
   {
      this.newPage(widget, changeHandler, true);
   }

   void newPage(DivViewChildElement widget, PageChangeHandler changeHandler, boolean history)
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
         DivViewChildElement currPage = this.pages.get(idx);
         this.remove((AbstractHtmlElementView) currPage);
         PageChangeHandler tmpChangeHandler = this.changeHandlers.get(idx);
         if (tmpChangeHandler != null)
         {
            tmpChangeHandler.pageHide();
         }
      }
      this.pages.add(widget);
      this.changeHandlers.add(changeHandler);
      this.appendChild(widget);
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
