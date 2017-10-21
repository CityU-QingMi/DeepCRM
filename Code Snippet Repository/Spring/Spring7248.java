	@Bean
	public MessageHandler userRegistryMessageHandler() {
		if (getBrokerRegistry().getUserRegistryBroadcast() == null) {
			return new NoOpMessageHandler();
		}
		SimpUserRegistry userRegistry = userRegistry();
		Assert.isInstanceOf(MultiServerUserRegistry.class, userRegistry, "MultiServerUserRegistry required");
		return new UserRegistryMessageHandler((MultiServerUserRegistry) userRegistry,
				brokerMessagingTemplate(), getBrokerRegistry().getUserRegistryBroadcast(),
				messageBrokerTaskScheduler());
	}
