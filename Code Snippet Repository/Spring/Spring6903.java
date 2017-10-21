	@Test
	public void setupJcaMessageContainerFullConfig() {
		JmsMessageEndpointManager container = new JmsMessageEndpointManager();
		MessageListener messageListener = new MessageListenerAdapter();
		SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
		endpoint.setDestination("myQueue");
		endpoint.setSelector("foo = 'bar'");
		endpoint.setSubscription("mySubscription");
		endpoint.setConcurrency("10");
		endpoint.setMessageListener(messageListener);

		endpoint.setupListenerContainer(container);
		JmsActivationSpecConfig config = container.getActivationSpecConfig();
		assertEquals("myQueue", config.getDestinationName());
		assertEquals("foo = 'bar'", config.getMessageSelector());
		assertEquals("mySubscription", config.getSubscriptionName());
		assertEquals(10, config.getMaxConcurrency());
		assertEquals(messageListener, container.getMessageListener());
	}
