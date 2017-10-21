    public EnumSet<DispatcherType> getDispatcherTypes()
    {
        EnumSet<DispatcherType> dispatcherTypes = EnumSet.noneOf(DispatcherType.class);
        if ((_dispatches & ERROR) == ERROR)
            dispatcherTypes.add(DispatcherType.ERROR);
        if ((_dispatches & FORWARD) == FORWARD)
            dispatcherTypes.add(DispatcherType.FORWARD);
        if ((_dispatches & INCLUDE) == INCLUDE)
            dispatcherTypes.add(DispatcherType.INCLUDE);
        if ((_dispatches & REQUEST) == REQUEST)
            dispatcherTypes.add(DispatcherType.REQUEST);
        if ((_dispatches & ASYNC) == ASYNC)
            dispatcherTypes.add(DispatcherType.ASYNC);
        return dispatcherTypes;
    }
