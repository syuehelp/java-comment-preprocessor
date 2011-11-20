/*
 * Copyright 2011 Igor Maznitsa (http://www.igormaznitsa.com)
 *
 * This library is free software; you can redistribute it and/or modify
 * it under the terms of version 3 of the GNU Lesser General Public
 * License as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307  USA
 */
package com.igormaznitsa.jcpreprocessor.expression.functions.xml;

import com.igormaznitsa.jcpreprocessor.context.PreprocessorContext;
import com.igormaznitsa.jcpreprocessor.expression.Value;
import com.igormaznitsa.jcpreprocessor.expression.ValueType;
import com.igormaznitsa.jcpreprocessor.expression.functions.AbstractFunction;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public final class FunctionXML_ELEMENTAT extends AbstractFunction {

    private static final ValueType[][] ARG_TYPES = new ValueType[][]{{ValueType.STRING, ValueType.INT}};

    @Override
    public String getName() {
        return "xml_elementat";
    }

    public Value executeStrInt(final PreprocessorContext context, final Value listId, final Value index) {
        final String listIdStr = listId.asString();
        final int indexAsInt = index.asLong().intValue();

        final String listElementId = listIdStr + '_' + indexAsInt;
        final Value result = Value.valueOf(listElementId);

        NodeContainer container = (NodeContainer) context.getSharedResource(listElementId);
        if (container == null) {

            container = (NodeContainer) context.getSharedResource(listIdStr);
            if (container == null) {
                throw new RuntimeException("Can't find any active node list for the id \'" + listIdStr + '\'');
            }

            final NodeList list = container.getNodeList();
            final Element element = (Element) list.item(indexAsInt);
            if (element == null) {
                throw new RuntimeException("Index is not valud [" + indexAsInt + ']');
            }

            container = new NodeContainer(UID_COUNTER.getAndIncrement(), element);
            context.setSharedResource(listElementId, container);
        }

        return result;
    }


    @Override
    public int getArity() {
        return 2;
    }

    @Override
    public ValueType[][] getAllowedArgumentTypes() {
        return ARG_TYPES;
    }

    @Override
    public String getReference() {
        return "it returns the element id of an element at the list position";
    }

    @Override
    public ValueType getResultType() {
        return ValueType.STRING;
    }
}