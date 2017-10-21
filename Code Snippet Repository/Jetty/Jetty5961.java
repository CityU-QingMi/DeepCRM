    @Override
    public void removeEventListener(EventListener listener)
    {
        super.removeEventListener(listener);
        if ((listener instanceof HttpSessionActivationListener)
            || (listener instanceof HttpSessionAttributeListener)
            || (listener instanceof HttpSessionBindingListener)
            || (listener instanceof HttpSessionListener)
            || (listener instanceof HttpSessionIdListener))
        {
            if (_sessionHandler!=null)
                _sessionHandler.removeEventListener(listener);
        }
        
    }
