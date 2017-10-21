    @Override
    public void addEventListener(EventListener listener)
    {
        super.addEventListener(listener);
        if ((listener instanceof HttpSessionActivationListener)
            || (listener instanceof HttpSessionAttributeListener)
            || (listener instanceof HttpSessionBindingListener)
            || (listener instanceof HttpSessionListener)
            || (listener instanceof HttpSessionIdListener))
        {
            if (_sessionHandler!=null)
                _sessionHandler.addEventListener(listener);
        }
    }
