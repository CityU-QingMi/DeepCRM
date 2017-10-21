    @Override
    public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp)
    {
        if (req.hasSubProtocol("listener-runtime-connect"))
        {
            return this.listenerRuntimeConnect;
        }
        else if (req.hasSubProtocol("annotated-runtime-connect"))
        {
            return this.annotatedRuntimeConnect;
        }

        return null;
    }
