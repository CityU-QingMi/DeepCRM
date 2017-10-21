        private Object call(Object obj, XmlParser.Node node) throws Exception
        {
            AttrOrElementNode aoeNode=new AttrOrElementNode(obj,node,"Id","Name","Class","Arg");
            String id = aoeNode.getString("Id");
            String name = aoeNode.getString("Name");
            String clazz = aoeNode.getString("Class");
            List<Object> args = aoeNode.getList("Arg");
            
            
            Class<?> oClass;
            if (clazz!=null)
            {
                // static call
                oClass=Loader.loadClass(clazz);
                obj=null;
            }
            else if (obj!=null)
            {
                oClass = obj.getClass();
            }
            else
                throw new IllegalArgumentException(node.toString());
           
            if (LOG.isDebugEnabled())
                LOG.debug("XML call " + name);

            try
            {
                Object nobj= TypeUtil.call(oClass,name,obj,args.toArray(new Object[args.size()]));
                if (id != null)
                    _configuration.getIdMap().put(id,nobj);
                configure(nobj,node,aoeNode.getNext());
                return nobj;
            }
            catch (NoSuchMethodException e)
            {
                IllegalStateException ise = new IllegalStateException("No Method: " + node + " on " + oClass);
                ise.initCause(e);
                throw ise;
            }
        }
