	@Test
	public void brokerChannel() {
		TestChannel channel = this.simpleBrokerContext.getBean("brokerChannel", TestChannel.class);
		Set<MessageHandler> handlers = channel.getSubscribers();

		assertEquals(2, handlers.size());
		assertTrue(handlers.contains(simpleBrokerContext.getBean(UserDestinationMessageHandler.class)));
		assertTrue(handlers.contains(simpleBrokerContext.getBean(SimpleBrokerMessageHandler.class)));

		assertNull(channel.getExecutor());
	}
