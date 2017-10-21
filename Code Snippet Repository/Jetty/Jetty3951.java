    public Servlet getServletInstance()
    {
        Servlet servlet=_servlet;
        if (servlet!=null)
            return servlet;
        synchronized(this)
        {
            return _servlet;
        }
    }
