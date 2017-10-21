            public List<Object> getList(String elementName, boolean manditory) throws Exception
            {
                String attrName=StringUtil.asciiToLowerCase(elementName);
                final List<Object> values=new ArrayList<>();
                
                String attr = _node.getAttribute(attrName);
                if (attr!=null)
                    values.addAll(StringUtil.csvSplit(null,attr,0,attr.length()));


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

                        values.add(value(_obj,n));
                    }
                }
                
                if (manditory && values.isEmpty())
                    throw new IllegalStateException("Must have attr '"+attrName+"' or element '"+elementName+"'");

                return values;
            }
