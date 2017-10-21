    @Override
    public void onConnect()
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug("onConnect({}, {})",jsrsession,config);
        }

        // Let unhandled exceptions flow out
        endpoint.onOpen(jsrsession,config);
    }
