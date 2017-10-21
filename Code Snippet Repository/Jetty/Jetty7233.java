        private synchronized void toString(StringBuilder buf, boolean tag)
        {
            if (tag)
            {
                buf.append("<");
                buf.append(_tag);

                if (_attrs != null)
                {
                    for (int i = 0; i < _attrs.length; i++)
                    {
                        buf.append(' ');
                        buf.append(_attrs[i].getName());
                        buf.append("=\"");
                        buf.append(_attrs[i].getValue());
                        buf.append("\"");
                    }
                }
            }

            if (_list != null)
            {
                if (tag)
                    buf.append(">");
                for (int i = 0; i < _list.size(); i++)
                {
                    Object o = _list.get(i);
                    if (o == null)
                        continue;
                    if (o instanceof Node)
                        ((Node) o).toString(buf, tag);
                    else
                        buf.append(o.toString());
                }
                if (tag)
                {
                    buf.append("</");
                    buf.append(_tag);
                    buf.append(">");
                }
            }
            else if (tag)
                buf.append("/>");
        }
