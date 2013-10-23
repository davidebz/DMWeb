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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;

/**
 * @author Davide Montesin <d@vide.bz>
 */
public abstract class AbstractHtmlElementView implements HtmlElementView
{

   public static boolean                 clientSide          = true;
   public static int                     clientSideIdSeq     = 0;

   private transient DMServerSideElement serverSideElement   = null;
   String                                id                  = null;
   private transient Element             clientSideElement__ = null;

   ArrayList<AttachListener>             attachHandlers      = null;
   /* 
    * This list will not contains all the childs nodes. Normally contains only child elements that
    * need to be informed about attach. Server side can contain temporary text node.
    */
   ArrayList<Node>                       childs              = null;

   int                                   eventBits           = 0;

   ArrayList<DMClickHandler>             clickHandlers       = null;

   protected AbstractHtmlElementView(String tagName)
   {
      this.attachHandlers = new ArrayList<AttachListener>();
      this.clickHandlers = new ArrayList<DMClickHandler>();
      this.childs = new ArrayList<Node>();

      if (clientSide)
      {
         this.clientSideElement__ = DOM.createElement(tagName);
         this.id = "E" + clientSideIdSeq;
         clientSideIdSeq++;
         this.clientSideElement__.setId(this.id);
      }
      else
      {
         this.serverSideElement = new DMServerSideElement(tagName);
      }

   }

   protected AbstractHtmlElementView(Void void1)
   {
   }

   @Override
   public Element getElement()
   {
      if (!clientSide)
      {
         throw new IllegalStateException("Element can be requested only on client not on server");
      }
      if (this.clientSideElement__ == null && this.id != null)
      {
         this.clientSideElement__ = DOM.getElementById(this.id);
      }
      return this.clientSideElement__;
   }

   public void setStyleName(String name)
   {
      if (clientSide)
      {
         this.getElement().setClassName(name);
      }
      else
      {
         this.serverSideElement.setClassName(name);
      }
   }

   public void addStyleName(String name)
   {
      if (clientSide)
      {
         this.getElement().addClassName(name);
      }
      else
      {
         this.serverSideElement.addClassName(name);
      }

   }

   public void removeStyleName(String name)
   {
      if (clientSide)
      {
         this.getElement().removeClassName(name);
      }
      else
      {
         this.serverSideElement.removeClassName(name);
      }
   }

   public void addAttachHandler(AttachListener attachHandler)
   {
      this.attachHandlers.add(attachHandler);
      if (clientSide && this.isAttacchedToDOM())
      {
         attachHandler.onAttachOrDetach(new AttachEvent(true));
      }
   }

   public void addClickHandler(DMClickHandler clickHandler)
   {
      this.clickHandlers.add(clickHandler);
      this.addSinkEvents(Event.ONCLICK);
   }

   public void clear()
   {
      ArrayList<Node> tmp = new ArrayList<Node>(this.childs);
      for (Node node : tmp)
      {
         if (node instanceof AbstractHtmlElementView)
         {
            this.remove((AbstractHtmlElementView) node);
         }
      }
      // some elements may be not in childs (those that are static: no attachlistner or reference)
      this.childs.clear();
      if (clientSide)
      {
         this.getElement().setInnerText("");
      }
   }

   protected void appendChildInternal(Node node)
   {
      if (node instanceof TextNode)
      {
         if (clientSide)
         {
            this.getElement().appendChild(Document.get().createTextNode(((TextNode) node).value));
         }
         else
         {
            this.childs.add(node);
         }
         return;
      }
      AbstractHtmlElementView widget = (AbstractHtmlElementView) node;
      this.childs.add(widget);
      if (clientSide)
      {
         this.getElement().appendChild(widget.getElement());

         if (this.isAttacchedToDOM())
         {
            this.notifyAttachRecursive(widget);
         }
      }
   }

   private void notifyAttachRecursive(AbstractHtmlElementView widget)
   {
      for (AttachListener attachHandler : widget.attachHandlers)
      {
         attachHandler.onAttachOrDetach(new AttachEvent(true));
      }
      for (Node child : widget.childs)
      {
         if (child instanceof AbstractHtmlElementView)
         {
            this.notifyAttachRecursive((AbstractHtmlElementView) child);
         }
      }
   }

   private void notifyDetachRecursive(AbstractHtmlElementView widget)
   {
      if (widget.isAttacchedToDOM()) // Child of an attached can be already unattached!
      {
         for (Node child : widget.childs)
         {
            if (child instanceof AbstractHtmlElementView)
            {
               this.notifyDetachRecursive((AbstractHtmlElementView) child);
            }
         }
         for (AttachListener attachHandler : widget.attachHandlers)
         {
            attachHandler.onAttachOrDetach(new AttachEvent(false));
         }
      }

   }

   public void remove(AbstractHtmlElementView dmWidget)
   {
      for (int i = 0; i < this.childs.size(); i++)
      {
         if (this.childs.get(i) == dmWidget)
         {
            this.childs.remove(i);
            if (clientSide)
            {
               if (this.isAttacchedToDOM())
               {
                  this.notifyDetachRecursive(dmWidget);
               }
               this.getElement().removeChild(dmWidget.getElement());
            }
            return;
         }
      }
      throw new IllegalArgumentException("Child not found!");
   }

   private boolean isEventAttachHandlerAlreadyRegistered()
   {
      for (AttachListener attachHandler : this.attachHandlers)
      {
         if (attachHandler instanceof DMWidgetEventAttachHandler)
         {
            return true;
         }
      }
      return false;
   }

   protected void addSinkEvents(int addEventBits)
   {
      this.eventBits |= addEventBits;
      if (!this.isEventAttachHandlerAlreadyRegistered())
      {
         this.addAttachHandler(new DMWidgetEventAttachHandler(this));
      }
      else if (this.isAttacchedToDOM())
      {
         DOM.sinkEvents(this.getElement(), this.eventBits);
      }
   }

   protected void onBrowserEvent(Event event)
   {
      String eventType = event.getType();
      if (eventType.equals("click"))
      {
         for (DMClickHandler clickHandler : this.clickHandlers)
         {
            clickHandler.onClick(new DMClickEvent(event));
         }
      }
   }

   boolean isAttacchedToDOM()
   {
      if (!clientSide)
      {
         throw new IllegalStateException("Only callable client side!");
      }
      Element elem = DOM.getElementById(this.id);
      if (elem != null) // isAttached!
      {
         // Replace the current element with this new! In this case the parent node is updated
         // even in case was attached not using gwt
         this.clientSideElement__ = elem;
         return true;
      }
      return false;

   }

   protected void setElementAttribute(String name, String value)
   {
      if (clientSide)
      {
         this.getElement().setAttribute(name, value);
      }
      else
      {
         value = AbstractHtmlElementView.escapeText4html(value);
         this.serverSideElement.attributes.put(name, value);
      }
   }

   public static void generateServerSideHtml(DMWidgetSerializationData serializationData,
                                             AbstractHtmlElementView widget,
                                             StringBuffer out)
   {
      DMServerSideElement element = widget.serverSideElement;

      out.append("<");
      out.append(element.tagName);

      String id = "E" + serializationData.idseq;
      serializationData.idseq++;

      out.append(" id=\"" + id + "\"");

      widget.id = id;

      if (element.styles.size() > 0)
      {
         out.append(" class=\"");
         for (String c : element.styles.keySet())
         {
            out.append(c);
            out.append(" ");
         }
         out.append("\"");
      }

      for (String k : element.attributes.keySet())
      {
         out.append(" ");
         out.append(k);
         out.append("=\"");
         out.append(element.attributes.get(k));
         out.append("\"");
      }
      out.append(">");

      if (!element.tagName.equals("img"))
      {

         for (int i = 0; i < widget.childs.size(); /* increment is in else statement */)
         {
            Node child = widget.childs.get(i);
            if (child instanceof TextNode)
            {
               out.append(escapeText4html(((TextNode) child).value));
               widget.childs.remove(i);
            }
            else
            {
               out.append("\n");
               generateServerSideHtml(serializationData, (AbstractHtmlElementView) child, out);
               out.append("\n");
               i++;
            }
         }

         out.append("</");
         out.append(element.tagName);
         out.append(">");
      }

      serializationData.attachHandlers.addAll(widget.attachHandlers);

   }

   public static String escapeText4html(String title)
   {
      StringBuffer result = new StringBuffer();
      for (int i = 0; i < title.length(); i++)
      {
         String singleChar = title.substring(i, i + 1);
         if (!singleChar.matches("[a-zA-Z0-9 /.()_:-]"))
         {
            // short versions? amp, apos, etc...
            int unicode = singleChar.charAt(0);
            String hex = Integer.toHexString(unicode);
            while (hex.length() < 4)
            {
               hex = "0" + hex;
            }
            singleChar = "&#x" + hex + ";";
         }
         result.append(singleChar);
      }
      return result.toString();
   }

   public static String escapeText4url(String title)
   {
      try
      {
         StringBuffer result = new StringBuffer();
         for (int i = 0; i < title.length(); i++)
         {
            String singleChar = title.substring(i, i + 1);
            if (!singleChar.matches("[a-zA-Z0-9]"))
            {
               byte[] utf8bytes = singleChar.getBytes("UTF-8"); // lower case does not work in gwt compiled code
               singleChar = "%";
               for (byte b : utf8bytes)
               {
                  String hexDigit = Integer.toHexString(b & 0xFF);
                  while (hexDigit.length() < 2)
                  {
                     hexDigit = "0" + hexDigit;
                  }
                  singleChar += hexDigit;
               }
            }
            result.append(singleChar);
         }
         return result.toString();
      }
      catch (UnsupportedEncodingException encodingException)
      {
         Window.alert("UnsupportedEncodingException");
         throw new RuntimeException(encodingException);
      }
   }
}
