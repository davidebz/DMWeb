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

import java.util.ArrayList;

import bz.davide.dmweb.shared.view.BoldView;
import bz.davide.dmweb.shared.view.BrView;
import bz.davide.dmweb.shared.view.DivView;
import bz.davide.dmweb.shared.view.SpanView;
import bz.davide.dmweb.shared.view.StrongView;
import bz.davide.dmxmljson.unmarshalling.xml.gwt.GWTXMLStructure;

/**
 * @author Davide Montesin <d@vide.bz>
 */
public class AbstractHtmlElement implements Node
{
   String                    tagName;

   protected ArrayList<Node> childNodes = new ArrayList<Node>();

   public AbstractHtmlElement(String tagName)
   {
      this.tagName = tagName;
   }

   protected AbstractHtmlElement(Void void1)
   {
   }

   public static void parseXhtml(String xhtmlText, Node rootNode) throws Exception
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
            e.appendChild(new SpanView(((TextNode) node).value));
         }
         else if (node instanceof B)
         {
            BoldView boldView = new BoldView();
            e.appendChild(boldView);
            richText(boldView, ((B) node).getChildNodes());
         }
         else if (node instanceof Strong)
         {
            StrongView strongView = new StrongView();
            e.appendChild(strongView);
            richText(strongView, ((Strong) node).getChildNodes());
         }
         else if (node instanceof Br)
         {
            BrView brView = new BrView();
            e.appendChild(brView);
         }
         else
         {
            throw new RuntimeException("Unknow tag: " + node.getClass().getName());
         }
      }
   }

   public static void richText(BoldView e, Node[] content)
   {
      for (Node node : content)
      {
         if (node instanceof TextNode)
         {
            e.appendChild(new SpanView(((TextNode) node).value));
         }
      }
   }

   public static void richText(StrongView e, Node[] content)
   {
      for (Node node : content)
      {
         if (node instanceof TextNode)
         {
            e.appendChild(new SpanView(((TextNode) node).value));
         }
      }
   }

   public Node[] getChildNodes()
   {
      return this.childNodes.toArray(new Node[0]);
   }

}
