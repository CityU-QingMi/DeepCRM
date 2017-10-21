    protected void requestDestroyed(Request baseRequest, HttpServletRequest request)
    {
        // Handle more REALLY SILLY request events!
        if (!_servletRequestListeners.isEmpty())
        {
            final ServletRequestEvent sre = new ServletRequestEvent(_scontext,request);
            for (int i=_servletRequestListeners.size();i-->0;)
                _servletRequestListeners.get(i).requestDestroyed(sre);
        }

        if (!_servletRequestAttributeListeners.isEmpty())
        {
            for (int i=_servletRequestAttributeListeners.size();i-->0;)
                baseRequest.removeEventListener(_servletRequestAttributeListeners.get(i));
        }
    }
