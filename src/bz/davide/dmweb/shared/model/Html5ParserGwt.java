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

import bz.davide.dmweb.shared.view.AnchorView;
import bz.davide.dmweb.shared.view.BoldView;
import bz.davide.dmweb.shared.view.BrView;
import bz.davide.dmweb.shared.view.DivView;
import bz.davide.dmweb.shared.view.ImgView;
import bz.davide.dmweb.shared.view.LiView;
import bz.davide.dmweb.shared.view.SpanView;
import bz.davide.dmweb.shared.view.StrongView;
import bz.davide.dmweb.shared.view.TextNodeView;
import bz.davide.dmweb.shared.view.UlView;
import bz.davide.dmxmljson.unmarshalling.xml.gwt.GWTXMLStructure;

/**
 * @author Davide Montesin <d@vide.bz>
 */
public class Html5ParserGwt
{
   public static void parseXhtml(String xhtmlText, AbstractHtmlElement rootNode) throws Exception
   {
      // Replace entities
      xhtmlText = xhtmlText.replaceAll("&nbsp;", "&#160;");

      Html5Unmarshaller html5Unmarshaller = new Html5Unmarshaller();
      GWTXMLStructure xhtml = new GWTXMLStructure(xhtmlText);
      html5Unmarshaller.unmarschall(xhtml, rootNode);
   }

   public static void richText(DivView e, Node[] content)
   {
      for (Node node : content)
      {
         if (node instanceof TextNode)
         {
            e.appendChild(new TextNodeView(((TextNode) node).value));
         }
         else if (node instanceof B)
         {
            BoldView boldView = new BoldView(new BoldView.InitParameters());
            e.appendChild(boldView);
            richText(boldView, ((B) node).getChildNodes());
         }
         else if (node instanceof Strong)
         {
            StrongView strongView = new StrongView(new StrongView.InitParameters());
            e.appendChild(strongView);
            richText(strongView, ((Strong) node).getChildNodes());
         }
         else if (node instanceof Span)
         {
            SpanView view = new SpanView(new SpanView.InitParameters());
            e.appendChild(view);
            richText(view, ((Span) node).getChildNodes());
         }
         else if (node instanceof Div)
         {
            DivView view = new DivView(new DivView.InitParameters());
            e.appendChild(view);
            richText(view, ((Div) node).getChildNodes());
         }
         else if (node instanceof A)
         {
            AnchorView view = new AnchorView(new AnchorView.InitParameters());
            view.setHref(((A) node).getHref());
            e.appendChild(view);
            richText(view, ((A) node).getChildNodes());
         }
         else if (node instanceof Br)
         {
            BrView brView = new BrView(new BrView.InitParameters());
            e.appendChild(brView);
         }
         else if (node instanceof Img)
         {
            ImgView view = new ImgView(new ImgView.InitParameters());
            e.appendChild(view);
            view.setSrc(((Img) node).getSrc());
         }
         else if (node instanceof Ul)
         {
            UlView view = new UlView(new UlView.InitParameters());
            e.appendChild(view);
            for (Node child : ((Ul) node).getChildNodes())
            {
               if (child instanceof Li)
               {
                  LiView liView = new LiView(new LiView.InitParameters());
                  view.appendChild(liView);
                  richText(liView, ((Li) child).getChildNodes());
               }
            }
         }
         else
         {
            throw new RuntimeException("Unknow tag: " + node.getClass().getName());
         }
      }
   }

   public static void richText(LiView e, Node[] content)
   {
      for (Node node : content)
      {
         if (node instanceof TextNode)
         {
            e.appendChild(new TextNodeView(((TextNode) node).value));
         }
         else if (node instanceof Ul)
         {
            UlView view = new UlView(new UlView.InitParameters());
            e.appendChild(view);
            for (Node child : ((Ul) node).getChildNodes())
            {
               if (child instanceof Li)
               {
                  LiView liView = new LiView(new LiView.InitParameters());
                  view.appendChild(liView);
                  richText(liView, ((Li) child).getChildNodes());
               }
            }
         }

      }
   }

   public static void richText(SpanView e, Node[] content)
   {
      for (Node node : content)
      {
         if (node instanceof TextNode)
         {
            e.appendChild(new TextNodeView(((TextNode) node).value));
         }
      }
   }

   public static void richText(AnchorView e, Node[] content)
   {
      for (Node node : content)
      {
         if (node instanceof TextNode)
         {
            e.appendChild(new TextNodeView(((TextNode) node).value));
         }
      }
   }

   public static void richText(BoldView e, Node[] content)
   {
      for (Node node : content)
      {
         if (node instanceof TextNode)
         {
            e.appendChild(new SpanView(new SpanView.InitParameters(((TextNode) node).value)));
         }
      }
   }

   public static void richText(StrongView e, Node[] content)
   {
      for (Node node : content)
      {
         if (node instanceof TextNode)
         {
            e.appendChild(new SpanView(new SpanView.InitParameters(((TextNode) node).value)));
         }
      }
   }

}
