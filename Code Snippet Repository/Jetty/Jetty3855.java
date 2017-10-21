    @Override
    public void initialize() throws Exception
    {
        if (!_initialized)
        {
            super.initialize();

            if (_filter==null)
            {
                try
                {
                    ServletContext context=_servletHandler.getServletContext();
                    _filter=(context instanceof ServletContextHandler.Context)
                            ?((ServletContextHandler.Context)context).createFilter(getHeldClass())
                            :getHeldClass().newInstance();
                }
                catch (ServletException se)
                {
                    Throwable cause = se.getRootCause();
                    if (cause instanceof InstantiationException)
                        throw (InstantiationException)cause;
                    if (cause instanceof IllegalAccessException)
                        throw (IllegalAccessException)cause;
                    throw se;
                }
            }

            _config=new Config();
            if (LOG.isDebugEnabled())
                LOG.debug("Filter.init {}",_filter);
            _filter.init(_config);
        }
        
        _initialized = true;
    }
