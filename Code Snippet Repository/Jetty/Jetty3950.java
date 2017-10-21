    public synchronized Servlet getServlet()
        throws ServletException
    {
        Servlet servlet=_servlet;
        if (servlet!=null && _unavailable==0)
            return servlet;

        synchronized(this)
        {
            // Handle previous unavailability
            if (_unavailable!=0)
            {
                if (_unavailable<0 || _unavailable>0 && System.currentTimeMillis()<_unavailable)
                    throw _unavailableEx;
                _unavailable=0;
                _unavailableEx=null;
            }

            servlet=_servlet;
            if (servlet!=null)
                return servlet;

            if (isRunning())
            {
                if (_class == null)
                    throw new UnavailableException("Servlet Not Initialized");
                if (_unavailable != 0 || !_initOnStartup)
                    initServlet();
                servlet=_servlet;
                if (servlet == null)
                    throw new UnavailableException("Could not instantiate " + _class);
            }

            return servlet;
        }
    }
