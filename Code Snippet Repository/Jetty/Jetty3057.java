    public void addEventListener(EventListener listener)
    {
        _eventListeners.add(listener);

        if (!(isStarted() || isStarting()))
            _durableListeners.add(listener);

        if (listener instanceof ContextScopeListener)
            _contextListeners.add((ContextScopeListener)listener);

        if (listener instanceof ServletContextListener)
            _servletContextListeners.add((ServletContextListener)listener);

        if (listener instanceof ServletContextAttributeListener)
            _servletContextAttributeListeners.add((ServletContextAttributeListener)listener);

        if (listener instanceof ServletRequestListener)
            _servletRequestListeners.add((ServletRequestListener)listener);

        if (listener instanceof ServletRequestAttributeListener)
            _servletRequestAttributeListeners.add((ServletRequestAttributeListener)listener);
    }
