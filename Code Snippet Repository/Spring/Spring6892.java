	@Test
	public void createJcaContainerFullConfig() {
		DefaultJcaListenerContainerFactory factory = new DefaultJcaListenerContainerFactory();
		setDefaultJcaConfig(factory);
		factory.setConcurrency("10");

		SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
		MessageListener messageListener = new MessageListenerAdapter();
		endpoint.setMessageListener(messageListener);
		endpoint.setDestination("myQueue");
		JmsMessageEndpointManager container = factory.createListenerContainer(endpoint);

		assertDefaultJcaConfig(container);
		assertEquals(10, container.getActivationSpecConfig().getMaxConcurrency());
		assertEquals(messageListener, container.getMessageListener());
		assertEquals("myQueue", container.getActivationSpecConfig().getDestinationName());
	}
