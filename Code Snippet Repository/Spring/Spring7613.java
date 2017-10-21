	@Test
	public void relayReconnectsIfBrokerComesBackUp() throws Exception {
		logger.debug("Starting test relayReconnectsIfBrokerComesBackUp()");

		String sess1 = "sess1";
		MessageExchange conn1 = MessageExchangeBuilder.connect(sess1).build();
		this.relay.handleMessage(conn1.message);
		this.responseHandler.expectMessages(conn1);

		String subs1 = "subs1";
		String destination = "/topic/test";
		MessageExchange subscribe = MessageExchangeBuilder.subscribeWithReceipt(sess1, subs1, destination, "r1").build();
		this.relay.handleMessage(subscribe.message);
		this.responseHandler.expectMessages(subscribe);

		MessageExchange error = MessageExchangeBuilder.error(sess1).build();
		stopActiveMqBrokerAndAwait();
		this.responseHandler.expectMessages(error);

		this.eventPublisher.expectBrokerAvailabilityEvent(false);

		startActiveMqBroker();
		this.eventPublisher.expectBrokerAvailabilityEvent(true);
	}
