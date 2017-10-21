	@Bean
	public AbstractBrokerMessageHandler stompBrokerRelayMessageHandler() {
		StompBrokerRelayMessageHandler handler = getBrokerRegistry().getStompBrokerRelay(brokerChannel());
		if (handler == null) {
			return new NoOpBrokerMessageHandler();
		}
		Map<String, MessageHandler> subscriptions = new HashMap<>(1);
		String destination = getBrokerRegistry().getUserDestinationBroadcast();
		if (destination != null) {
			subscriptions.put(destination, userDestinationMessageHandler());
		}
		destination = getBrokerRegistry().getUserRegistryBroadcast();
		if (destination != null) {
			subscriptions.put(destination, userRegistryMessageHandler());
		}
		handler.setSystemSubscriptions(subscriptions);
		return handler;
	}
