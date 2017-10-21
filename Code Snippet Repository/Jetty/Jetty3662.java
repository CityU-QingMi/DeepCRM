    @Test
    public void testAddFirstConnectionFactory() throws Exception
    {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        server.addConnector(connector);

        HttpConnectionFactory http = new HttpConnectionFactory();
        connector.addConnectionFactory(http);
        ProxyConnectionFactory proxy = new ProxyConnectionFactory();
        connector.addFirstConnectionFactory(proxy);

        Collection<ConnectionFactory> factories = connector.getConnectionFactories();
        assertEquals(2, factories.size());
        assertSame(proxy, factories.iterator().next());
        assertEquals(2, connector.getBeans(ConnectionFactory.class).size());
        assertEquals(proxy.getProtocol(), connector.getDefaultProtocol());
    }
