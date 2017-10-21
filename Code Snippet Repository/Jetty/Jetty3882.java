        @Override
        public RequestDispatcher getNamedDispatcher(String name)
        {
            ContextHandler context=org.eclipse.jetty.servlet.ServletContextHandler.this;
            if (_servletHandler==null)
                return null;
            ServletHolder holder = _servletHandler.getServlet(name);
            if (holder==null || !holder.isEnabled())
                return null;
            return new Dispatcher(context, name);
        }
