/*
DMWeb - Java web framework - http://www.davide.bz/dmweb

Copyright (C) 2014 Davide Montesin <d@vide.bz> - Bolzano/Bozen - Italy

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

package bz.davide.dmweb.client.leaflet;

public class DistanceCalculator
{
   final static double R = 6371000; // or 6.372,795 ?

   public static double distanceMeter(double latA, double lonA, double latB, double lonB)
   {
      double latArad = toRad(latA);
      double lonArad = toRad(lonA);
      double latBrad = toRad(latB);
      double lonBrad = toRad(lonB);

      double v1 = Math.sin(latArad) *
                  Math.sin(latBrad) +
                  Math.cos(latArad) *
                  Math.cos(latBrad) *
                  Math.cos(lonArad - lonBrad);

      double ab = R * Math.acos(v1);
      return ab;
   }

   static double toRad(double latLon)
   {
      return latLon * Math.PI / 180D;
   }

   static double toDegree(double latLon)
   {
      return latLon * 180D / Math.PI;
   }

   public static double calculateLongitude(double latA, double lonA, double distanceMeter)
   {
      double latArad = toRad(latA);
      double lonArad = toRad(lonA);

      double latBrad = latArad;

      double ab = distanceMeter;

      double v1 = Math.cos(ab / R);

      double v2 = (v1 - Math.sin(latArad) * Math.sin(latBrad)) / (Math.cos(latArad) * Math.cos(latBrad));

      double lonArad_lonBrad = Math.acos(v2);

      double lonBrad = -lonArad_lonBrad + lonArad;

      double d = toDegree(lonBrad);

      return d;

   }

   public static double calculateLatitude(double latA, double distanceMeter)
   {
      double latArad = toRad(latA);

      double delta = distanceMeter / R;

      double latBrad = latArad + delta;

      double d = toDegree(latBrad);

      return d;

   }
}
