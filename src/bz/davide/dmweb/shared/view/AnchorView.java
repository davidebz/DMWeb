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
public class AnchorView extends AbstractHtmlElementView implements DivViewChildElement, LiViewChild
{

   public static class InitParameters extends AbstractHtmlElementView.InitParameters
   {
      private String href;
      private String text;
      private String target;

      public InitParameters()
      {
         this("#", "-Link-", null);
      }

      public InitParameters(String href, String text)
      {
         this(href, text, null);
      }

      public InitParameters(String href, String text, String target)
      {
         super("a");
         this.href = href;
         this.text = text;
         this.target = target;
      }

   }

   public AnchorView(InitParameters initParameters)
   {
      super(initParameters);
      if (initParameters.href != null)
      {
         this.setHref(initParameters.href);
      }
      if (initParameters.text != null)
      {
         this.setText(initParameters.text);
      }
      if (initParameters.target != null)
      {
         this.setTarget(initParameters.target);
      }
   }

   protected AnchorView()
   {
   }

   public void setHref(String href)
   {
      this.setElementAttribute("href", href);
   }

   public void setText(String text)
   {
      this.clear();
      this.appendChild(new TextNodeView(text));
   }

   public void setTarget(String target)
   {
      this.setElementAttribute("target", target);
   }

   public void appendChild(AnchorViewChild node)
   {
      super.appendChildInternal(node);
   }
}
