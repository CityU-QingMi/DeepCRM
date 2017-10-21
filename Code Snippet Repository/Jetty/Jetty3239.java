    public void removeEventListener(EventListener listener)
    {
        if (listener instanceof HttpSessionAttributeListener)
            _sessionAttributeListeners.remove(listener);
        if (listener instanceof HttpSessionListener)
            _sessionListeners.remove(listener);
        if (listener instanceof HttpSessionIdListener)
            _sessionIdListeners.remove(listener);
        removeBean(listener);
    }
