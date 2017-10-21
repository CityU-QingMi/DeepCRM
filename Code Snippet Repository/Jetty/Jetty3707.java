    private void checkWildcardHost(boolean succeed, Server server, String[] contextHosts, String[] requestHosts) throws Exception
    {
        LocalConnector connector = (LocalConnector)server.getConnectors()[0];
        ContextHandlerCollection handlerCollection = (ContextHandlerCollection)server.getHandler();
        ContextHandler context = (ContextHandler)handlerCollection.getHandlers()[0];
        IsHandledHandler handler = (IsHandledHandler)context.getHandler();

        context.setVirtualHosts(contextHosts);
        // trigger this manually; it's supposed to be called when adding the handler
        handlerCollection.mapContexts();

        for(String host : requestHosts)
        {
            // System.err.printf("host=%s in %s%n",host,contextHosts==null?Collections.emptyList():Arrays.asList(contextHosts));
            
            String response=connector.getResponse("GET / HTTP/1.0\n" + "Host: "+host+"\nConnection:close\n\n");
            // System.err.println(response);
            if(succeed)
                assertTrue("'"+host+"' should have been handled.",handler.isHandled());
            else
                assertFalse("'"+host + "' should not have been handled.", handler.isHandled());
            handler.reset();
        }

    }
