    public void testEmptyStack() throws ConfigurationException {
        final String filename = "com/opensymphony/xwork2/config/providers/xwork-unknownhandler-stack-empty.xml";
        ConfigurationProvider provider = buildConfigurationProvider(filename);
        loadConfigurationProviders(provider);
        configurationManager.reload();

        UnknownHandlerManager unknownHandlerManager = new DefaultUnknownHandlerManager();
        container.inject(unknownHandlerManager);
        List<UnknownHandler> unknownHandlers = unknownHandlerManager.getUnknownHandlers();

        assertNotNull(unknownHandlers);
        assertEquals(2, unknownHandlers.size());

        UnknownHandler uh1 = unknownHandlers.get(0);
        UnknownHandler uh2 = unknownHandlers.get(1);

        assertTrue(uh1 instanceof SomeUnknownHandler);
        assertTrue(uh2 instanceof SomeUnknownHandler);
    }
