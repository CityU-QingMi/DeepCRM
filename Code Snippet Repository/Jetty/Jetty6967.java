    private WebSocketSession createSession(URI requestURI, EventDriver websocket, LogicalConnection connection)
    {
        if (websocket == null)
        {
            throw new InvalidWebSocketException("Unable to create Session from null websocket");
        }
        
        for (SessionFactory impl : sessionFactories)
        {
            if (impl.supports(websocket))
            {
                try
                {
                    return impl.createSession(requestURI, websocket, connection);
                }
                catch (Throwable e)
                {
                    throw new InvalidWebSocketException("Unable to create Session", e);
                }
            }
        }
        
        throw new InvalidWebSocketException("Unable to create Session: unrecognized internal EventDriver type: " + websocket.getClass().getName());
    }
