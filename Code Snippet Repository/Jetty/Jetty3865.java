    public void setDispatcherTypes(EnumSet<DispatcherType> dispatcherTypes)
    {
        _dispatches=DEFAULT;
        if (dispatcherTypes!=null)
        {
            if (dispatcherTypes.contains(DispatcherType.ERROR))
                _dispatches|=ERROR;
            if (dispatcherTypes.contains(DispatcherType.FORWARD))
                _dispatches|=FORWARD;
            if (dispatcherTypes.contains(DispatcherType.INCLUDE))
                _dispatches|=INCLUDE;
            if (dispatcherTypes.contains(DispatcherType.REQUEST))
                _dispatches|=REQUEST;
            if (dispatcherTypes.contains(DispatcherType.ASYNC))
                _dispatches|=ASYNC;
        }
    }
