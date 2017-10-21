	@Test
	public void clientInboundChannelWithBrokerRelay() {
		TestChannel channel = this.brokerRelayContext.getBean("clientInboundChannel", TestChannel.class);
		Set<MessageHandler> handlers = channel.getSubscribers();

		assertEquals(3, handlers.size());
		assertTrue(handlers.contains(brokerRelayContext.getBean(SimpAnnotationMethodMessageHandler.class)));
		assertTrue(handlers.contains(brokerRelayContext.getBean(UserDestinationMessageHandler.class)));
		assertTrue(handlers.contains(brokerRelayContext.getBean(StompBrokerRelayMessageHandler.class)));
	}
