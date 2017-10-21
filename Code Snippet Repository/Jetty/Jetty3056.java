    public void setEventListeners(EventListener[] eventListeners)
    {
        _contextListeners.clear();
        _servletContextListeners.clear();
        _servletContextAttributeListeners.clear();
        _servletRequestListeners.clear();
        _servletRequestAttributeListeners.clear();
        _eventListeners.clear();

        if (eventListeners!=null)
            for (EventListener listener : eventListeners)
                addEventListener(listener);
    }
