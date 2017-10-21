    @Override
    protected AbstractConnection newServerConnection(Connector connector, EndPoint endPoint, SSLEngine engine, List<String> protocols, String defaultProtocol)
    {
        for (Server processor : processors)
        {
            if (processor.appliesTo(engine))
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("{} for {} on {}", processor, engine, endPoint);
                ALPNServerConnection connection = new ALPNServerConnection(connector, endPoint, engine, protocols, defaultProtocol);
                processor.configure(engine, connection);
                return connection;
            }
        }
        throw new IllegalStateException("No ALPNProcessor for " + engine);
    }
