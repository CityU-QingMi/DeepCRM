        @Override
        public <T extends Servlet> T createServlet(Class<T> c) throws ServletException
        {
            try
            {
                T s = createInstance(c);
                s = _objFactory.decorate(s);
                return s;
            }
            catch (Exception e)
            {
                throw new ServletException(e);
            }
        }
