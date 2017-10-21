    @Override
    public void destroyInstance (Object o)
    throws Exception
    {
        if (o==null)
            return;
        Servlet servlet =  ((Servlet)o);
        getServletHandler().destroyServlet(servlet);
        servlet.destroy();
    }
