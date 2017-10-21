	@Test
	public void userBroadcasts() throws Exception {
		SimpUserRegistry userRegistry = this.brokerRelayContext.getBean(SimpUserRegistry.class);
		assertEquals(MultiServerUserRegistry.class, userRegistry.getClass());

		UserDestinationMessageHandler handler1 = this.brokerRelayContext.getBean(UserDestinationMessageHandler.class);
		assertEquals("/topic/unresolved-user-destination", handler1.getBroadcastDestination());

		UserRegistryMessageHandler handler2 = this.brokerRelayContext.getBean(UserRegistryMessageHandler.class);
		assertEquals("/topic/simp-user-registry", handler2.getBroadcastDestination());

		StompBrokerRelayMessageHandler relay = this.brokerRelayContext.getBean(StompBrokerRelayMessageHandler.class);
		assertNotNull(relay.getSystemSubscriptions());
		assertEquals(2, relay.getSystemSubscriptions().size());
		assertSame(handler1, relay.getSystemSubscriptions().get("/topic/unresolved-user-destination"));
		assertSame(handler2, relay.getSystemSubscriptions().get("/topic/simp-user-registry"));
	}
