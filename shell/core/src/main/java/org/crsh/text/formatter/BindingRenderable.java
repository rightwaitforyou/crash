/*
 * Copyright (C) 2012 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.crsh.text.formatter;

import org.crsh.text.Renderable;
import org.crsh.text.Renderer;
import org.crsh.text.ui.LabelElement;
import org.crsh.text.ui.RowElement;
import org.crsh.text.ui.TableElement;

import javax.naming.Binding;
import java.awt.*;
import java.util.Iterator;

/**
 * @author <a href="mailto:alain.defrance@exoplatform.com">Alain Defrance</a>
 */
public class BindingRenderable extends Renderable<BindingRenderable.BindingData> {
  @Override
  public Class<BindingData> getType() {
    return BindingData.class;
  }

  @Override
  public Renderer renderer(Iterator<BindingData> stream) {

    TableElement table = new TableElement();
    table.setRightCellPadding(1);
    RowElement header = new RowElement(true);
    header.add(new LabelElement("NAME"));
    table.add(header);

    while (stream.hasNext()) {
      BindingData binding = stream.next();

      RowElement row = new RowElement();

       row.add(new LabelElement(binding.name));

      if (binding.verbose) {
        row.add(new LabelElement(binding.type));
        if (header.getSize() == 1) {
          header.add(new LabelElement("CLASS"));
        }
      }
      
      table.add(row);


    }

    return table.renderer();
  }

  public static class BindingData {

    public final String name;
    public final String type;
    public final Boolean verbose;

    public BindingData(String name, String type,Boolean verbose) {
      this.name = name;
      this.type = type;
      this.verbose = verbose;
    }
  }

}
