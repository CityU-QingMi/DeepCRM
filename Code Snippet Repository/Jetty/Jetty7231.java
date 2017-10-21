        private Object get(Object obj, XmlParser.Node node) throws Exception
        {
            Class<?> oClass = nodeClass(node);
            if (oClass != null)
                obj = null;
            else
                oClass = obj.getClass();

            String name = node.getAttribute("name");
            String id = node.getAttribute("id");
            if (LOG.isDebugEnabled())
                LOG.debug("XML get " + name);

            try
            {
                // Handle getClass explicitly
                if ("class".equalsIgnoreCase(name))
                    obj=oClass;
                else
                {
                    // try calling a getXxx method.
                    Method method = oClass.getMethod("get" + name.substring(0,1).toUpperCase(Locale.ENGLISH) + name.substring(1),(java.lang.Class[])null);
                    obj = method.invoke(obj,(java.lang.Object[])null);
                }
                if (id!=null)
                    _configuration.getIdMap().put(id,obj);
                configure(obj,node,0);
            }
            catch (NoSuchMethodException nsme)
            {
                try
                {
                    Field field = oClass.getField(name);
                    obj = field.get(obj);
                    configure(obj,node,0);
                }
                catch (NoSuchFieldException nsfe)
                {
                    throw nsme;
                }
            }
            return obj;
        }
