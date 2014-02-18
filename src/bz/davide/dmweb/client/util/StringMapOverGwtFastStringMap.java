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

package bz.davide.dmweb.client.util;

import java.util.ArrayList;

import com.google.gwt.core.client.JavaScriptObject;

public class StringMapOverGwtFastStringMap<T>
{
   JavaScriptObject mapStringToIndex;
   ArrayList<T> objects;

   public StringMapOverGwtFastStringMap()
   {
      this.mapStringToIndex = newMap();
      this.objects = new ArrayList<T>();
   }

   public T get(Object key)
   {
      int pos = getNative(mapStringToIndex, (String)key);
      if (pos < 0)
      {
         return null;
      }
      return objects.get(pos);
   }

   public T put(String key, T obj)
   {
      // This key already has an index?
      int pos = getNative(mapStringToIndex, key);
      T ret = null;
      if (pos >= 0)
      {
         ret = objects.get(pos);
         objects.set(pos, obj);
      }
      else
      {
         pos = objects.size();
         objects.add(obj);
         putNative(mapStringToIndex, key, pos);
      }
      return ret;
   }

   static native int getNative(JavaScriptObject map, String key)/*-{
      var ret = map[key];
      if (typeof(ret) !== 'undefined')
         return ret;
      return -1;
   }-*/;

   static native void putNative(JavaScriptObject map, String key, int pos)/*-{
      map[key] = pos;
   }-*/;


   static native JavaScriptObject newMap()/*-{
      return {};
   }-*/;

}
