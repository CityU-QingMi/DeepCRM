	@Bean
	public UserDestinationMessageHandler userDestinationMessageHandler() {
		UserDestinationMessageHandler handler = new UserDestinationMessageHandler(clientInboundChannel(),
				brokerChannel(), userDestinationResolver());
		String destination = getBrokerRegistry().getUserDestinationBroadcast();
		if (destination != null) {
			handler.setBroadcastDestination(destination);
		}
		return handler;
	}
