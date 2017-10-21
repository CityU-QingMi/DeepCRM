        private Object newArray(Object obj, XmlParser.Node node) throws Exception
        {
            AttrOrElementNode aoeNode=new AttrOrElementNode(obj,node,"Id","Type","Item");
            String id = aoeNode.getString("Id");
            String type = aoeNode.getString("Type");
            List<XmlParser.Node> items = aoeNode.getNodes("Item");
            
            // Get the type
            Class<?> aClass = java.lang.Object.class;
            if (type != null)
            {
                aClass = TypeUtil.fromName(type);
                if (aClass == null)
                {
                    switch (type)
                    {
                        case "String":
                            aClass = String.class;
                            break;
                        case "URL":
                            aClass = URL.class;
                            break;
                        case "InetAddress":
                            aClass = InetAddress.class;
                            break;
                        default:
                            aClass = Loader.loadClass(type);
                            break;
                    }
                }
            }
            
            Object al = null;

            for (XmlParser.Node item : items)
            {
                String nid = item.getAttribute("id");
                Object v = value(obj,item);
                al = LazyList.add(al,(v == null && aClass.isPrimitive())?0:v);
                if (nid != null)
                    _configuration.getIdMap().put(nid,v);
            }

            Object array = LazyList.toArray(al,aClass);
            if (id != null)
                _configuration.getIdMap().put(id,array);
            return array;
        }
