    public EventDriver wrap(Object websocket)
    {
        if (websocket == null)
        {
            throw new InvalidWebSocketException("null websocket object");
        }

        for (EventDriverImpl impl : implementations)
        {
            if (impl.supports(websocket))
            {
                try
                {
                    return impl.create(websocket,containerScope.getPolicy().clonePolicy());
                }
                catch (Throwable e)
                {
                    throw new InvalidWebSocketException("Unable to create websocket",e);
                }
            }
        }

        // Create a clear error message for the developer
        StringBuilder err = new StringBuilder();
        err.append(getClassName(websocket));
        err.append(" is not a valid WebSocket object.");
        err.append("  Object must obey one of the following rules: ");

        int len = implementations.size();
        for (int i = 0; i < len; i++)
        {
            EventDriverImpl impl = implementations.get(i);
            if (i > 0)
            {
                err.append(" or ");
            }
            err.append("\n(").append(i + 1).append(") ");
            err.append(impl.describeRule());
        }

        throw new InvalidWebSocketException(err.toString());
    }
