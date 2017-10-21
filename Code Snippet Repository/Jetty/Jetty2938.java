    @Override
    public Object getAttribute(String name)
    {
        if (name.startsWith("org.eclipse.jetty"))
        {
            if (Server.class.getName().equals(name))
                return _channel.getServer();
            if (HttpChannel.class.getName().equals(name))
                return _channel;
            if (HttpConnection.class.getName().equals(name) &&
                _channel.getHttpTransport() instanceof HttpConnection)
                return _channel.getHttpTransport();
        }
        return (_attributes == null)?null:_attributes.getAttribute(name);
    }
