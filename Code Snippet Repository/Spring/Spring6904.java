	@Test
	public void setupConcurrencySimpleContainer() {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		MessageListener messageListener = new MessageListenerAdapter();
		SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
		endpoint.setConcurrency("5-10"); // simple implementation only support max value
		endpoint.setMessageListener(messageListener);

		endpoint.setupListenerContainer(container);
		assertEquals(10, new DirectFieldAccessor(container).getPropertyValue("concurrentConsumers"));
	}
