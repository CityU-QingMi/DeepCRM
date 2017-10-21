        public Object configure(Object obj) throws Exception
        {
            // Check the class of the object
            Class<?> oClass = nodeClass(_root);
            if (oClass != null && !oClass.isInstance(obj))
            {
                String loaders = (oClass.getClassLoader()==obj.getClass().getClassLoader())?"":"Object Class and type Class are from different loaders.";
                throw new IllegalArgumentException("Object of class '"+obj.getClass().getCanonicalName()+"' is not of type '" + oClass.getCanonicalName()+"'. "+loaders+" in "+_url);
            }
            String id=_root.getAttribute("id");
            if (id!=null)
                _configuration.getIdMap().put(id,obj);
            configure(obj,_root,0);
            return obj;
        }
