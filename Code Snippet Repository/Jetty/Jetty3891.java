        @Override
        public <T extends Filter> T createFilter(Class<T> c) throws ServletException
        {
            try
            {
                T f = createInstance(c);
                f = _objFactory.decorate(f);
                return f;
            }
            catch (Exception e)
            {
                throw new ServletException(e);
            }
        }
