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

package bz.davide.dmweb.shared.component.example;

import java.util.ArrayList;

import bz.davide.dmweb.shared.view.DivView;
import bz.davide.dmweb.shared.view.ImgView;
import bz.davide.dmweb.shared.view.SpanView;

import com.google.gwt.user.client.Timer;

/**
 * @author Davide Montesin <d@vide.bz>
 */
public class PhotoGallery extends DivView
{
   ArrayList<DivView> imageBoxes = new ArrayList<DivView>();
   int                lastIndex;
   int                currIndex;

   public PhotoGallery(String[] labels, String[] imageUrls)
   {
      this.setStyleName("photo-gallery");
      for (int i = 0; i < labels.length && i < imageUrls.length; i++)
      {
         DivView imageBox = this.createImageBox(labels[i], imageUrls[i]);
         this.imageBoxes.add(imageBox);
         this.appendChild(imageBox);
      }
      this.addAttachHandler(new PhotoGalleryAttachListener(this));
   }

   public void start()
   {
      for (int i = 0; i < this.imageBoxes.size(); i++)
      {
         this.imageBoxes.get(i).getElement().getStyle().setZIndex(i);
      }
      this.lastIndex = 0;
      this.currIndex = 0;
      this.imageBoxes.get(this.currIndex).getElement().getStyle().setOpacity(1);

      final int TIME = 2000;

      final Timer timer = new Timer()
      {
         @Override
         public void run()
         {
            final Timer t1 = this;
            PhotoGallery.this.currIndex++;
            PhotoGallery.this.currIndex = PhotoGallery.this.currIndex % PhotoGallery.this.imageBoxes.size();
            PhotoGallery.this.imageBoxes.get(PhotoGallery.this.lastIndex).getElement().getStyle().setOpacity(0);
            PhotoGallery.this.imageBoxes.get(PhotoGallery.this.currIndex).getElement().getStyle().setOpacity(1);
            Timer t2 = new Timer()
            {
               @Override
               public void run()
               {
                  PhotoGallery.this.lastIndex = PhotoGallery.this.currIndex;
                  t1.schedule(TIME);
               }
            };
            t2.schedule(700);
         }
      };
      timer.schedule(TIME);
   }

   protected PhotoGallery(Void void1)
   {
      super(void1);
   }

   DivView createImageBox(String text, String imgUrl)
   {
      DivView imageBox = new DivView("image-box");
      imageBox.appendChild(new SpanView(text));
      imageBox.appendChild(new ImgView(imgUrl));
      return imageBox;
   }
}
