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


/**
 * @author Davide Montesin <d@vide.bz>
 */
public class ImgView extends AbstractHtmlElementView
{
   public ImgView()
   {
      this("missing-url.png");
   }

   public ImgView(String url)
   {
      this(url, "");
   }

   public ImgView(String url, String altText)
   {
      super("img");
      this.setSrc(url);
      this.setAltText(altText);
   }

   protected ImgView(Void void1)
   {
      super(void1);
   }

   public void setSrc(String url)
   {
      this.setElementAttribute("src", url);
   }

   public void setAltText(String text)
   {
      this.setElementAttribute("alt", text);
   }

}
