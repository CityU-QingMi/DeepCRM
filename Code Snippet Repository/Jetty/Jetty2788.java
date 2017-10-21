    public void error(ServletRequest request, ServletResponse response) throws ServletException, IOException
    {
        try
        {
            request.setAttribute(__ERROR_DISPATCH,Boolean.TRUE);
            forward(request, response, DispatcherType.ERROR);
        }
        finally
        {
            request.setAttribute(__ERROR_DISPATCH,null);
        }
    }
