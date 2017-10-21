    @Override
    public void doHandle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        final DispatcherType dispatch = baseRequest.getDispatcherType();
        final boolean new_context = baseRequest.takeNewContext();
        try
        {
            if (new_context)
                requestInitialized(baseRequest,request);

            switch(dispatch)
            {
                case REQUEST:
                    if (isProtectedTarget(target))
                    {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND);
                        baseRequest.setHandled(true);
                        return;
                    }
                    break;
                    
                case ERROR:
                    // If this is already a dispatch to an error page, proceed normally
                    if (Boolean.TRUE.equals(baseRequest.getAttribute(Dispatcher.__ERROR_DISPATCH)))
                        break;
                    
                    // We can just call doError here.  If there is no error page, then one will
                    // be generated. If there is an error page, then a RequestDispatcher will be
                    // used to route the request through appropriate filters etc.
                    doError(target,baseRequest,request,response);
                    return;
                default:
                    break;
            }

            nextHandle(target,baseRequest,request,response);
        }
        finally
        {
            if (new_context)
                requestDestroyed(baseRequest,request);
        }
    }
