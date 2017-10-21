	@Test
	public void testConcurrency() {
		// JMS
		DefaultMessageListenerContainer listener0 = this.context
				.getBean(DefaultMessageListenerContainer.class.getName() + "#0", DefaultMessageListenerContainer.class);
		DefaultMessageListenerContainer listener1 = this.context
				.getBean("listener1", DefaultMessageListenerContainer.class);
		DefaultMessageListenerContainer listener2 = this.context
				.getBean("listener2", DefaultMessageListenerContainer.class);

		assertEquals("Wrong concurrency on listener using placeholder", 2, listener0.getConcurrentConsumers());
		assertEquals("Wrong concurrency on listener using placeholder", 3, listener0.getMaxConcurrentConsumers());
		assertEquals("Wrong concurrency on listener1", 3, listener1.getConcurrentConsumers());
		assertEquals("Wrong max concurrency on listener1", 5, listener1.getMaxConcurrentConsumers());
		assertEquals("Wrong custom concurrency on listener2", 5, listener2.getConcurrentConsumers());
		assertEquals("Wrong custom max concurrency on listener2", 10, listener2.getMaxConcurrentConsumers());

		// JCA
		JmsMessageEndpointManager listener3 = this.context
				.getBean("listener3", JmsMessageEndpointManager.class);
		JmsMessageEndpointManager listener4 = this.context
				.getBean("listener4", JmsMessageEndpointManager.class);
		assertEquals("Wrong concurrency on listener3", 5,
				listener3.getActivationSpecConfig().getMaxConcurrency());
		assertEquals("Wrong custom concurrency on listener4", 7,
				listener4.getActivationSpecConfig().getMaxConcurrency());
	}
