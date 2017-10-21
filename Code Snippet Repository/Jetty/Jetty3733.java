    private void checkWildcardHost(boolean succeed, Server server, String[] contextHosts, String[] requestHosts) throws Exception
    {
        LocalConnector connector = (LocalConnector)server.getConnectors()[0];
        ContextHandler context = (ContextHandler)server.getHandler();
        context.setVirtualHosts(contextHosts);

        IsHandledHandler handler = (IsHandledHandler)context.getHandler();
        for(String host : requestHosts)
        {
            connector.getResponse("GET / HTTP/1.1\n" + "Host: "+host+"\nConnection:close\n\n");
            if(succeed)
                Assert.assertTrue("'" + host + "' should have been handled.", handler.isHandled());
            else
                Assert.assertFalse("'" + host + "' should not have been handled.", handler.isHandled());
            handler.reset();
        }

    }
