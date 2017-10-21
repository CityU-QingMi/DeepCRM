	public void testCustomConfiguration(ApplicationContext context) {
		JmsListenerContainerTestFactory defaultFactory =
				context.getBean("jmsListenerContainerFactory", JmsListenerContainerTestFactory.class);
		JmsListenerContainerTestFactory customFactory =
				context.getBean("customFactory", JmsListenerContainerTestFactory.class);
		assertEquals(1, defaultFactory.getListenerContainers().size());
		assertEquals(1, customFactory.getListenerContainers().size());
		JmsListenerEndpoint endpoint = defaultFactory.getListenerContainers().get(0).getEndpoint();
		assertEquals("Wrong endpoint type", SimpleJmsListenerEndpoint.class, endpoint.getClass());
		assertEquals("Wrong listener set in custom endpoint", context.getBean("simpleMessageListener"),
				((SimpleJmsListenerEndpoint) endpoint).getMessageListener());

		JmsListenerEndpointRegistry customRegistry =
				context.getBean("customRegistry", JmsListenerEndpointRegistry.class);
		assertEquals("Wrong number of containers in the registry", 2,
				customRegistry.getListenerContainerIds().size());
		assertEquals("Wrong number of containers in the registry", 2,
				customRegistry.getListenerContainers().size());
		assertNotNull("Container with custom id on the annotation should be found",
				customRegistry.getListenerContainer("listenerId"));
		assertNotNull("Container created with custom id should be found",
				customRegistry.getListenerContainer("myCustomEndpointId"));
	}
