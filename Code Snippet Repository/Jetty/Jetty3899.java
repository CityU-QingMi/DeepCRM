        @Override
        public <T extends EventListener> T createListener(Class<T> clazz) throws ServletException
        {
            try
            {
                T l = createInstance(clazz);
                l = _objFactory.decorate(l);
                return l;
            }            
            catch (Exception e)
            {
                throw new ServletException(e);
            }
        }
