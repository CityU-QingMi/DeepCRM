    @Override
    public int getServerPort()
    {
        MetaData.Request metadata = _metaData;
        HttpURI uri = metadata==null?null:metadata.getURI();
        int port = (uri==null||uri.getHost()==null)?findServerPort():uri.getPort();

        // If no port specified, return the default port for the scheme
        if (port <= 0)
        {
            if (getScheme().equalsIgnoreCase(URIUtil.HTTPS))
                return 443;
            return 80;
        }

        // return a specific port
        return port;
    }
