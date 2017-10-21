    protected void startContext() throws Exception
    {
        String managedAttributes = _initParams.get(MANAGED_ATTRIBUTES);
        if (managedAttributes != null)
            addEventListener(new ManagedAttributeListener(this,StringUtil.csvSplit(managedAttributes)));

        super.doStart();

        // Call context listeners
        _destroySerletContextListeners.clear();
        if (!_servletContextListeners.isEmpty())
        {
            ServletContextEvent event = new ServletContextEvent(_scontext);
            for (ServletContextListener listener:_servletContextListeners)
            {
                callContextInitialized(listener, event);
                _destroySerletContextListeners.add(listener);
            }
        }
    }
