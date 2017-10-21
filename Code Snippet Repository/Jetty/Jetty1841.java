    @Override
    public void doStart() throws Exception
    {
        boolean rmi = "rmi".equals(_jmxURL.getProtocol());
        if (rmi)
        {
            if (!_environment.containsKey(RMIConnectorServer.RMI_SERVER_SOCKET_FACTORY_ATTRIBUTE))
                _environment.put(RMIConnectorServer.RMI_SERVER_SOCKET_FACTORY_ATTRIBUTE, new JMXRMIServerSocketFactory(_jmxURL.getHost(), port -> _rmiPort = port));
            if (_sslContextFactory != null)
            {
                SslRMIClientSocketFactory csf = new SslRMIClientSocketFactory();
                if (!_environment.containsKey(RMIConnectorServer.RMI_CLIENT_SOCKET_FACTORY_ATTRIBUTE))
                    _environment.put(RMIConnectorServer.RMI_CLIENT_SOCKET_FACTORY_ATTRIBUTE, csf);
                if (!_environment.containsKey(RMI_REGISTRY_CLIENT_SOCKET_FACTORY_ATTRIBUTE))
                    _environment.put(RMI_REGISTRY_CLIENT_SOCKET_FACTORY_ATTRIBUTE, csf);
            }
        }

        String urlPath = _jmxURL.getURLPath();
        String jndiRMI = "/jndi/rmi://";
        if (urlPath.startsWith(jndiRMI))
        {
            int startIndex = jndiRMI.length();
            int endIndex = urlPath.indexOf('/', startIndex);
            HostPort hostPort = new HostPort(urlPath.substring(startIndex, endIndex));
            String registryHost = startRegistry(hostPort);
            // If the RMI registry was already started, use the existing port.
            if (_registryPort == 0)
                _registryPort = hostPort.getPort();
            urlPath = jndiRMI + registryHost + ":" + _registryPort + urlPath.substring(endIndex);
            // Rebuild JMXServiceURL to use it for the creation of the JMXConnectorServer.
            _jmxURL = new JMXServiceURL(_jmxURL.getProtocol(), _jmxURL.getHost(), _jmxURL.getPort(), urlPath);
        }

        MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
        _connectorServer = JMXConnectorServerFactory.newJMXConnectorServer(_jmxURL, _environment, mbeanServer);
        mbeanServer.registerMBean(_connectorServer, new ObjectName(_objectName));
        _connectorServer.start();
        String rmiHost = normalizeHost(_jmxURL.getHost());
        // If _rmiPort is still zero, it's using the same port as the RMI registry.
        if (_rmiPort == 0)
            _rmiPort = _registryPort;
        _jmxURL = new JMXServiceURL(_jmxURL.getProtocol(), rmiHost, _rmiPort, urlPath);

        ShutdownThread.register(0, this);

        LOG.info("JMX URL: {}", _jmxURL);
    }
