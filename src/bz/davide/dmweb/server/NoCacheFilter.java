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

package bz.davide.dmweb.server;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Davide Montesin <d@vide.bz>
 */
public class NoCacheFilter implements Filter
{
   @Override
   public void init(FilterConfig arg0) throws ServletException
   {
   }

   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
                                                                                            ServletException
   {
      if (request instanceof HttpServletRequest)
      {
         // Cache-Control is ignored when response status is 304 Not Modified
         // max-age=0, must-revalidate: browsers uses the local cached version when using back-forward. Re-validate on bookmark or url enter or link.
         // If you need back-forward revalidate you can try with no-cache
         ((HttpServletResponse) response).setHeader("Cache-Control", "max-age=0, must-revalidate, private");
      }
      chain.doFilter(request, response);
   }

   @Override
   public void destroy()
   {
   }
}
