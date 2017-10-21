    public AsyncContextEvent(Context context,AsyncContextState asyncContext, HttpChannelState state, Request baseRequest, ServletRequest request, ServletResponse response)
    {
        super(null,request,response,null);
        _context=context;
        _asyncContext=asyncContext;
        _state=state;

        // If we haven't been async dispatched before
        if (baseRequest.getAttribute(AsyncContext.ASYNC_REQUEST_URI)==null)
        {
            // We are setting these attributes during startAsync, when the spec implies that
            // they are only available after a call to AsyncContext.dispatch(...);

            // have we been forwarded before?
            String uri=(String)baseRequest.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);
            if (uri!=null)
            {
                baseRequest.setAttribute(AsyncContext.ASYNC_REQUEST_URI,uri);
                baseRequest.setAttribute(AsyncContext.ASYNC_CONTEXT_PATH,baseRequest.getAttribute(RequestDispatcher.FORWARD_CONTEXT_PATH));
                baseRequest.setAttribute(AsyncContext.ASYNC_SERVLET_PATH,baseRequest.getAttribute(RequestDispatcher.FORWARD_SERVLET_PATH));
                baseRequest.setAttribute(AsyncContext.ASYNC_PATH_INFO,baseRequest.getAttribute(RequestDispatcher.FORWARD_PATH_INFO));
                baseRequest.setAttribute(AsyncContext.ASYNC_QUERY_STRING,baseRequest.getAttribute(RequestDispatcher.FORWARD_QUERY_STRING));
            }
            else
            {
                baseRequest.setAttribute(AsyncContext.ASYNC_REQUEST_URI,baseRequest.getRequestURI());
                baseRequest.setAttribute(AsyncContext.ASYNC_CONTEXT_PATH,baseRequest.getContextPath());
                baseRequest.setAttribute(AsyncContext.ASYNC_SERVLET_PATH,baseRequest.getServletPath());
                baseRequest.setAttribute(AsyncContext.ASYNC_PATH_INFO,baseRequest.getPathInfo());
                baseRequest.setAttribute(AsyncContext.ASYNC_QUERY_STRING,baseRequest.getQueryString());
            }
        }
    }
