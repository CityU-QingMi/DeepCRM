	@Test
	public void testResponseDestination() {
		// JMS
		DefaultMessageListenerContainer listener1 = this.context
				.getBean("listener1", DefaultMessageListenerContainer.class);
		DefaultMessageListenerContainer listener2 = this.context
				.getBean("listener2", DefaultMessageListenerContainer.class);
		assertEquals("Wrong destination type on listener1", true, listener1.isPubSubDomain());
		assertEquals("Wrong destination type on listener2", true, listener2.isPubSubDomain());
		assertEquals("Wrong response destination type on listener1", false, listener1.isReplyPubSubDomain());
		assertEquals("Wrong response destination type on listener2", false, listener2.isReplyPubSubDomain());

		// JCA
		JmsMessageEndpointManager listener3 = this.context
				.getBean("listener3", JmsMessageEndpointManager.class);
		JmsMessageEndpointManager listener4 = this.context
				.getBean("listener4", JmsMessageEndpointManager.class);
		assertEquals("Wrong destination type on listener3", true, listener3.isPubSubDomain());
		assertEquals("Wrong destination type on listener4", true, listener4.isPubSubDomain());
		assertEquals("Wrong response destination type on listener3", false, listener3.isReplyPubSubDomain());
		assertEquals("Wrong response destination type on listener4", false, listener4.isReplyPubSubDomain());
	}
