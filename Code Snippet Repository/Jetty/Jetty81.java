        public void handle(Class<?> clazz)
        {
            Class<?> c = clazz;
            
            //process the whole inheritance hierarchy for the class
            while (c!=null && (!c.equals(Object.class)))
            {
                doHandle(c);
                if (!_introspectAncestors)
                    break;
                
                c = c.getSuperclass();
            }   
        }
