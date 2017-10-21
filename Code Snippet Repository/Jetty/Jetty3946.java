    protected Servlet newInstance() throws ServletException, IllegalAccessException, InstantiationException
    {
        try
        {
            ServletContext ctx = getServletHandler().getServletContext();
            if (ctx instanceof ServletContextHandler.Context)
                return ((ServletContextHandler.Context)ctx).createServlet(getHeldClass());
            return getHeldClass().newInstance();
        }
        catch (ServletException se)
        {
            Throwable cause = se.getRootCause();
            if (cause instanceof InstantiationException)
                throw (InstantiationException)cause;
            if (cause instanceof IllegalAccessException)
                throw (IllegalAccessException)cause;
            throw se;
        }
    }
