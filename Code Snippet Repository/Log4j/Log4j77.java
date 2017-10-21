    static void init() {
        contextMap = null;
        final PropertiesUtil managerProps = PropertiesUtil.getProperties();
        disableAll = managerProps.getBooleanProperty(DISABLE_ALL);
        useStack = !(managerProps.getBooleanProperty(DISABLE_STACK) || disableAll);
        useMap = !(managerProps.getBooleanProperty(DISABLE_MAP) || disableAll);

        contextStack = new DefaultThreadContextStack(useStack);
        if (!useMap) {
            contextMap = new NoOpThreadContextMap();
        } else {
            contextMap = ThreadContextMapFactory.createThreadContextMap();
        }
        if (contextMap instanceof ReadOnlyThreadContextMap) {
            readOnlyContextMap = (ReadOnlyThreadContextMap) contextMap;
        }
    }
