	@Test
	public void clientInboundChannel() {
		TestChannel channel = this.simpleBrokerContext.getBean("clientInboundChannel", TestChannel.class);
		Set<MessageHandler> handlers = channel.getSubscribers();

		assertEquals(3, handlers.size());
		assertTrue(handlers.contains(simpleBrokerContext.getBean(SimpAnnotationMethodMessageHandler.class)));
		assertTrue(handlers.contains(simpleBrokerContext.getBean(UserDestinationMessageHandler.class)));
		assertTrue(handlers.contains(simpleBrokerContext.getBean(SimpleBrokerMessageHandler.class)));
	}
