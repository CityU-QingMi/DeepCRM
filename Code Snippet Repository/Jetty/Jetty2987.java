    public void handleAsync(HttpChannel channel) throws IOException, ServletException
    {
        final HttpChannelState state = channel.getRequest().getHttpChannelState();
        final AsyncContextEvent event = state.getAsyncContextEvent();

        final Request baseRequest=channel.getRequest();
        final String path=event.getPath();

        if (path!=null)
        {
            // this is a dispatch with a path
            ServletContext context=event.getServletContext();
            String query=baseRequest.getQueryString();
            baseRequest.setURIPathQuery(URIUtil.addEncodedPaths(context==null?null:URIUtil.encodePath(context.getContextPath()), path));
            HttpURI uri = baseRequest.getHttpURI();
            baseRequest.setPathInfo(uri.getDecodedPath());
            if (uri.getQuery()!=null)
                baseRequest.mergeQueryParameters(query,uri.getQuery(), true); //we have to assume dispatch path and query are UTF8
        }

        final String target=baseRequest.getPathInfo();
        final HttpServletRequest request=(HttpServletRequest)event.getSuppliedRequest();
        final HttpServletResponse response=(HttpServletResponse)event.getSuppliedResponse();

        if (LOG.isDebugEnabled())
            LOG.debug("{} {} {} on {}", request.getDispatcherType(), request.getMethod(), target, channel);
        handle(target, baseRequest, request, response);
        if (LOG.isDebugEnabled())
            LOG.debug("handledAsync={} async={} committed={} on {}", channel.getRequest().isHandled(),request.isAsyncStarted(),response.isCommitted(),channel);
    }
