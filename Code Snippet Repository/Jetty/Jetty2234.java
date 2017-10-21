    public OverlayedAppProvider()
    {
        try
        {
            _nodeName=InetAddress.getLocalHost().getHostName();
        }
        catch(UnknownHostException e)
        {
            __log.debug(e);
            _nodeName="unknown";
        }
    }
