    @SuppressWarnings("")
    private String getServerUrl()
    {
        NetworkConnector connector=null;
        for (Connector c: getServer().getConnectors())
        {
            if (c instanceof NetworkConnector)
            {
                connector=(NetworkConnector)c;
                break;
            }
        }

        if (connector==null)
            return "http://localhost";

        return "http://localhost:" + connector.getPort();
    }
