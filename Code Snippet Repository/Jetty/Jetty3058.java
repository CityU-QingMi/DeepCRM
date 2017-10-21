    public void removeEventListener(EventListener listener)
    {
        _eventListeners.remove(listener);

        if (listener instanceof ContextScopeListener)
            _contextListeners.remove(listener);

        if (listener instanceof ServletContextListener)
            _servletContextListeners.remove(listener);

        if (listener instanceof ServletContextAttributeListener)
            _servletContextAttributeListeners.remove(listener);

        if (listener instanceof ServletRequestListener)
            _servletRequestListeners.remove(listener);

        if (listener instanceof ServletRequestAttributeListener)
            _servletRequestAttributeListeners.remove(listener);
    }
