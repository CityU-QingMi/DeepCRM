    public void setServletHandler(ServletHandler servletHandler)
    {
        if (isStarted())
            throw new IllegalStateException("STARTED");

        Handler next=null;
        if (_servletHandler!=null)
        {
            next=_servletHandler.getHandler();
            _servletHandler.setHandler(null);
            replaceHandler(_servletHandler,servletHandler);
        }
        _servletHandler = servletHandler;
        if (next!=null && _servletHandler.getHandler()==null)
            _servletHandler.setHandler(next);
        relinkHandlers();
    }
