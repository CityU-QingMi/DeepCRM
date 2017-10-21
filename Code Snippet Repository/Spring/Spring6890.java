	@Test
	public void createSimpleContainer() {
		SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
		setDefaultJmsConfig(factory);
		SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();

		MessageListener messageListener = new MessageListenerAdapter();
		endpoint.setMessageListener(messageListener);
		endpoint.setDestination("myQueue");

		SimpleMessageListenerContainer container = factory.createListenerContainer(endpoint);

		assertDefaultJmsConfig(container);
		assertEquals(messageListener, container.getMessageListener());
		assertEquals("myQueue", container.getDestinationName());
	}
