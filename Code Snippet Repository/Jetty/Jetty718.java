    public static Continuation getContinuation(ServletRequest request)
    {
        Continuation continuation = (Continuation) request.getAttribute(Continuation.ATTRIBUTE);
        if (continuation!=null)
            return continuation;

        while (request instanceof ServletRequestWrapper)
            request=((ServletRequestWrapper)request).getRequest();

        if (__servlet3 )
        {
            try
            {
                continuation=__newServlet3Continuation.newInstance(request);
                request.setAttribute(Continuation.ATTRIBUTE,continuation);
                return continuation;
            }
            catch(Exception e)
            {
                throw new RuntimeException(e);
            }
        }

        throw new IllegalStateException("!(Jetty || Servlet 3.0 || ContinuationFilter)");
    }
