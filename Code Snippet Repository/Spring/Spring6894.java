	@Test
	public void backOffOverridesRecoveryInterval() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		BackOff backOff = new FixedBackOff();
		factory.setBackOff(backOff);
		factory.setRecoveryInterval(2000L);

		SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
		MessageListener messageListener = new MessageListenerAdapter();
		endpoint.setMessageListener(messageListener);
		endpoint.setDestination("myQueue");
		DefaultMessageListenerContainer container = factory.createListenerContainer(endpoint);

		assertSame(backOff, new DirectFieldAccessor(container).getPropertyValue("backOff"));
	}
