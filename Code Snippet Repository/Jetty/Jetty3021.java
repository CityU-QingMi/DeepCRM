    protected void requestInitialized(Request baseRequest, HttpServletRequest request)
    {
        // Handle the REALLY SILLY request events!
        if (!_servletRequestAttributeListeners.isEmpty())
            for (ServletRequestAttributeListener l :_servletRequestAttributeListeners)
                baseRequest.addEventListener(l);

        if (!_servletRequestListeners.isEmpty())
        {
            final ServletRequestEvent sre = new ServletRequestEvent(_scontext,request);
            for (ServletRequestListener l : _servletRequestListeners)
                l.requestInitialized(sre);
        }
    }
