	@Test
	public void brokerBecomingUnvailableTriggersErrorFrame() throws Exception {
		logger.debug("Starting test brokerBecomingUnvailableTriggersErrorFrame()");

		String sess1 = "sess1";
		MessageExchange connect = MessageExchangeBuilder.connect(sess1).build();
		this.relay.handleMessage(connect.message);
		this.responseHandler.expectMessages(connect);

		MessageExchange error = MessageExchangeBuilder.error(sess1).build();
		stopActiveMqBrokerAndAwait();
		this.eventPublisher.expectBrokerAvailabilityEvent(false);
		this.responseHandler.expectMessages(error);
	}
