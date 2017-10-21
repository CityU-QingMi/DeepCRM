    public static DispatcherType dispatch(String type)
    {
        if ("request".equalsIgnoreCase(type))
            return DispatcherType.REQUEST;
        if ("forward".equalsIgnoreCase(type))
            return DispatcherType.FORWARD;
        if ("include".equalsIgnoreCase(type))
            return DispatcherType.INCLUDE;
        if ("error".equalsIgnoreCase(type))
            return DispatcherType.ERROR;
        if ("async".equalsIgnoreCase(type))
            return DispatcherType.ASYNC;
        throw new IllegalArgumentException(type);
    }
