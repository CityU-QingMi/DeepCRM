	private void createAndStartRelay() throws InterruptedException {
		this.relay = new StompBrokerRelayMessageHandler(new StubMessageChannel(),
				this.responseChannel, new StubMessageChannel(), Arrays.asList("/queue/", "/topic/"));
		this.relay.setRelayPort(this.port);
		this.relay.setApplicationEventPublisher(this.eventPublisher);
		this.relay.setSystemHeartbeatReceiveInterval(0);
		this.relay.setSystemHeartbeatSendInterval(0);

		this.relay.start();
		this.eventPublisher.expectBrokerAvailabilityEvent(true);
	}
