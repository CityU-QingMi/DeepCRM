	protected StompBrokerRelayMessageHandler getMessageHandler(SubscribableChannel brokerChannel) {

		StompBrokerRelayMessageHandler handler = new StompBrokerRelayMessageHandler(
				getClientInboundChannel(), getClientOutboundChannel(),
				brokerChannel, getDestinationPrefixes());

		handler.setRelayHost(this.relayHost);
		handler.setRelayPort(this.relayPort);

		handler.setClientLogin(this.clientLogin);
		handler.setClientPasscode(this.clientPasscode);

		handler.setSystemLogin(this.systemLogin);
		handler.setSystemPasscode(this.systemPasscode);

		if (this.systemHeartbeatSendInterval != null) {
			handler.setSystemHeartbeatSendInterval(this.systemHeartbeatSendInterval);
		}
		if (this.systemHeartbeatReceiveInterval != null) {
			handler.setSystemHeartbeatReceiveInterval(this.systemHeartbeatReceiveInterval);
		}
		if (this.virtualHost != null) {
			handler.setVirtualHost(this.virtualHost);
		}

		handler.setAutoStartup(this.autoStartup);

		return handler;
	}
