            public Object get(String elementName, boolean manditory) throws Exception
            {
                String attrName=StringUtil.asciiToLowerCase(elementName);
                String attr = _node.getAttribute(attrName);
                Object value=attr;
                
                for (int i=0;i<_next;i++)
                {
                    Object o = _node.get(i);
                    if (!(o instanceof XmlParser.Node))
                        continue;
                    XmlParser.Node n = (XmlParser.Node)o;
                    if (elementName.equals(n.getTag()))
                    {
                        if (attr!=null)
                            throw new IllegalStateException("Cannot have attr '"+attrName+"' and element '"+elementName+"'");

                        value=value(_obj,n);
                        break;
                    }
                }
                
                if (manditory && value==null)
                    throw new IllegalStateException("Must have attr '"+attrName+"' or element '"+elementName+"'");
                
                return value;
            }
