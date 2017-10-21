    @Test
    public void testServiceLoader()
    {
        System.out.printf("Service Name: %s%n",ServerEndpointConfig.Configurator.class.getName());

        ServiceLoader<ServerEndpointConfig.Configurator> loader = ServiceLoader.load(javax.websocket.server.ServerEndpointConfig.Configurator.class);
        assertThat("loader",loader,notNullValue());
        Iterator<ServerEndpointConfig.Configurator> iter = loader.iterator();
        assertThat("loader.iterator",iter,notNullValue());
        assertThat("loader.iterator.hasNext",iter.hasNext(),is(true));

        ServerEndpointConfig.Configurator configr = iter.next();
        assertThat("Configurator",configr,notNullValue());
        assertThat("Configurator type",configr,instanceOf(ContainerDefaultConfigurator.class));
    }
