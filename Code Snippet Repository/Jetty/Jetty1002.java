    @After
    public void dispose() throws Exception
    {
        for (int i = clients.size() - 1; i >= 0; i--)
        {
            HTTP2Client client = clients.get(i);
            client.stop();
        }
        for (int i = servers.size() - 1; i >= 0; i--)
        {
            Server server = servers.get(i);
            server.stop();
        }
    }
