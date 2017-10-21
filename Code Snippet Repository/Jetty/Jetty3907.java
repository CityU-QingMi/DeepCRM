    @Override
    protected synchronized void doStart()
        throws Exception
    {
        ContextHandler.Context context=ContextHandler.getCurrentContext();
        _servletContext=context==null?new ContextHandler.StaticContext():context;
        _contextHandler=(ServletContextHandler)(context==null?null:context.getContextHandler());

        if (_contextHandler!=null)
        {
            SecurityHandler security_handler = _contextHandler.getChildHandlerByClass(SecurityHandler.class);
            if (security_handler!=null)
                _identityService=security_handler.getIdentityService();
        }

        updateNameMappings();
        updateMappings();        
        
        if (getServletMapping("/")==null && isEnsureDefaultServlet())
        {
            if (LOG.isDebugEnabled())
                LOG.debug("Adding Default404Servlet to {}",this);
            addServletWithMapping(Default404Servlet.class,"/");
            updateMappings();  
            getServletMapping("/").setDefault(true);
        }

        if (isFilterChainsCached())
        {
            _chainCache[FilterMapping.REQUEST]=new ConcurrentHashMap<>();
            _chainCache[FilterMapping.FORWARD]=new ConcurrentHashMap<>();
            _chainCache[FilterMapping.INCLUDE]=new ConcurrentHashMap<>();
            _chainCache[FilterMapping.ERROR]=new ConcurrentHashMap<>();
            _chainCache[FilterMapping.ASYNC]=new ConcurrentHashMap<>();

            _chainLRU[FilterMapping.REQUEST]=new ConcurrentLinkedQueue<>();
            _chainLRU[FilterMapping.FORWARD]=new ConcurrentLinkedQueue<>();
            _chainLRU[FilterMapping.INCLUDE]=new ConcurrentLinkedQueue<>();
            _chainLRU[FilterMapping.ERROR]=new ConcurrentLinkedQueue<>();
            _chainLRU[FilterMapping.ASYNC]=new ConcurrentLinkedQueue<>();
        }

        if (_contextHandler==null)
            initialize();
        
        super.doStart();
    }
