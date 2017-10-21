    @Override
    public void include(ServletRequest request, ServletResponse response) throws ServletException, IOException
    {
        Request baseRequest=Request.getBaseRequest(request);

        if (!(request instanceof HttpServletRequest))
            request = new ServletRequestHttpWrapper(request);
        if (!(response instanceof HttpServletResponse))
            response = new ServletResponseHttpWrapper(response);

        final DispatcherType old_type = baseRequest.getDispatcherType();
        final Attributes old_attr=baseRequest.getAttributes();
        final MultiMap<String> old_query_params=baseRequest.getQueryParameters();
        try
        {
            baseRequest.setDispatcherType(DispatcherType.INCLUDE);
            baseRequest.getResponse().include();
            if (_named!=null)
            {
                _contextHandler.handle(_named,baseRequest, (HttpServletRequest)request, (HttpServletResponse)response);
            }
            else
            {
                IncludeAttributes attr = new IncludeAttributes(old_attr);

                attr._requestURI=_uri.getPath();
                attr._contextPath=_contextHandler.getContextPath();
                attr._servletPath=null; // set by ServletHandler
                attr._pathInfo=_pathInContext;
                attr._query=_uri.getQuery();

                if (attr._query!=null)
                    baseRequest.mergeQueryParameters(baseRequest.getQueryString(),attr._query, false);
                baseRequest.setAttributes(attr);

                _contextHandler.handle(_pathInContext, baseRequest, (HttpServletRequest)request, (HttpServletResponse)response);
            }
        }
        finally
        {
            baseRequest.setAttributes(old_attr);
            baseRequest.getResponse().included();
            baseRequest.setQueryParameters(old_query_params);
            baseRequest.resetParameters();
            baseRequest.setDispatcherType(old_type);
        }
    }
