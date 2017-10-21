    @Override
    public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException
    {
        if (_asyncNotSupportedSource!=null)
            throw new IllegalStateException("!asyncSupported: "+_asyncNotSupportedSource);
        HttpChannelState state = getHttpChannelState();
        if (_async==null)
            _async=new AsyncContextState(state);
        AsyncContextEvent event = new AsyncContextEvent(_context,_async,state,this,servletRequest,servletResponse);
        event.setDispatchContext(getServletContext());
        
        String uri = ((HttpServletRequest)servletRequest).getRequestURI();
        if (_contextPath!=null && uri.startsWith(_contextPath))
            uri = uri.substring(_contextPath.length());
        else
            // TODO probably need to strip encoded context from requestURI, but will do this for now:
            uri = URIUtil.encodePath(URIUtil.addPaths(getServletPath(),getPathInfo()));  
        
        event.setDispatchPath(uri);
        state.startAsync(event);
        return _async;
    }
