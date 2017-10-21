    public URI getURI()
    {
        NetworkConnector connector=null;
        for (Connector c: _connectors)
        {
            if (c instanceof NetworkConnector)
            {
                connector=(NetworkConnector)c;
                break;
            }
        }

        if (connector==null)
            return null;

        ContextHandler context = getChildHandlerByClass(ContextHandler.class);

        try
        {
            String protocol = connector.getDefaultConnectionFactory().getProtocol();
            String scheme="http";
            if (protocol.startsWith("SSL-") || protocol.equals("SSL"))
                scheme = "https";

            String host=connector.getHost();
            if (context!=null && context.getVirtualHosts()!=null && context.getVirtualHosts().length>0)
                host=context.getVirtualHosts()[0];
            if (host==null)
                host=InetAddress.getLocalHost().getHostAddress();

            String path=context==null?null:context.getContextPath();
            if (path==null)
                path="/";
            return new URI(scheme,null,host,connector.getLocalPort(),path,null,null);
        }
        catch(Exception e)
        {
            LOG.warn(e);
            return null;
        }
    }
