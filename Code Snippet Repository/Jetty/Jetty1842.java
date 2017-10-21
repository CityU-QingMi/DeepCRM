    private String startRegistry(HostPort hostPort) throws Exception
    {
        String host = hostPort.getHost();
        int port = hostPort.getPort(1099);

        try
        {
            // Check if a local registry is already running.
            LocateRegistry.getRegistry(host, port).list();
            return normalizeHost(host);
        }
        catch (Throwable ex)
        {
            LOG.ignore(ex);
        }

        RMIClientSocketFactory csf = _sslContextFactory == null ? null : new SslRMIClientSocketFactory();
        RMIServerSocketFactory ssf = new JMXRMIServerSocketFactory(host, p -> _registryPort = p);
        _registry = LocateRegistry.createRegistry(port, csf, ssf);

        return normalizeHost(host);
    }
