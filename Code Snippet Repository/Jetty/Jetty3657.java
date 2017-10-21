    private URI toServerURI(ServerConnector connector) throws URISyntaxException
    {
        String host = connector.getHost();
        if (host == null)
        {
            host = "localhost";
        }
        int port = connector.getLocalPort();
        return new URI(String.format("http://%s:%d/",host,port));
    }
