    @Test
    public void testJMXOverTLS() throws Exception
    {
        SslContextFactory sslContextFactory = new SslContextFactory();
        String keyStorePath = MavenTestingUtils.getTestResourcePath("keystore.jks").toString();
        String keyStorePassword = "storepwd";
        sslContextFactory.setKeyStorePath(keyStorePath);
        sslContextFactory.setKeyStorePassword(keyStorePassword);
        sslContextFactory.start();

        // The RMIClientSocketFactory is stored within the RMI stub.
        // When using TLS, the stub is deserialized in a possibly different
        // JVM that does not have access to the server keystore, and there
        // is no way to provide TLS configuration during the deserialization
        // of the stub. Therefore the client must provide system properties
        // to specify the TLS configuration. For this test it needs the
        // trustStore because the server certificate is self-signed.
        // The server needs to contact the RMI registry and therefore also
        // needs these system properties.
        System.setProperty("javax.net.ssl.trustStore", keyStorePath);
        System.setProperty("javax.net.ssl.trustStorePassword", keyStorePassword);

        connectorServer = new ConnectorServer(new JMXServiceURL("rmi", null, 1100, "/jndi/rmi://localhost:1100/jmxrmi"), null, objectName, sslContextFactory);
        connectorServer.start();

        // The client needs to talk TLS to the RMI registry to download
        // the RMI server stub, and this is independent from JMX.
        // The RMI server stub then contains the SslRMIClientSocketFactory
        // needed to talk to the RMI server.
        Map<String, Object> clientEnv = new HashMap<>();
        clientEnv.put(ConnectorServer.RMI_REGISTRY_CLIENT_SOCKET_FACTORY_ATTRIBUTE, new SslRMIClientSocketFactory());
        try (JMXConnector client = JMXConnectorFactory.connect(connectorServer.getAddress(), clientEnv))
        {
            client.getMBeanServerConnection().queryNames(null, null);
        }
    }
