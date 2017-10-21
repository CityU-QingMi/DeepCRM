	@Test
	public void userBroadcastsDisabledWithSimpleBroker() throws Exception {
		SimpUserRegistry registry = this.simpleBrokerContext.getBean(SimpUserRegistry.class);
		assertNotNull(registry);
		assertNotEquals(MultiServerUserRegistry.class, registry.getClass());

		UserDestinationMessageHandler handler = this.simpleBrokerContext.getBean(UserDestinationMessageHandler.class);
		assertNull(handler.getBroadcastDestination());

		String name = "userRegistryMessageHandler";
		MessageHandler messageHandler = this.simpleBrokerContext.getBean(name, MessageHandler.class);
		assertNotEquals(UserRegistryMessageHandler.class, messageHandler.getClass());
	}
