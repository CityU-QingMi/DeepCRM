            public List<XmlParser.Node> getNodes(String elementName) throws Exception
            {
                String attrName=StringUtil.asciiToLowerCase(elementName);
                final List<XmlParser.Node> values=new ArrayList<>();
                
                String attr = _node.getAttribute(attrName);
                if (attr!=null)
                {
                    for (String a : StringUtil.csvSplit(null,attr,0,attr.length()))
                    {
                        // create a fake node
                        XmlParser.Node n = new XmlParser.Node(null,elementName,null);
                        n.add(a);
                        values.add(n);
                    }
                }

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

                        values.add(n);
                    }
                }

                return values;
            }
