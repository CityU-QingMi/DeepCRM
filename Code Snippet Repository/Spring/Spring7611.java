	@Test(expected = MessageDeliveryException.class)
	public void messageDeliveryExceptionIfSystemSessionForwardFails() throws Exception {

		logger.debug("Starting test messageDeliveryExceptionIfSystemSessionForwardFails()");

		stopActiveMqBrokerAndAwait();
		this.eventPublisher.expectBrokerAvailabilityEvent(false);

		StompHeaderAccessor headers = StompHeaderAccessor.create(StompCommand.SEND);
		this.relay.handleMessage(MessageBuilder.createMessage("test".getBytes(), headers.getMessageHeaders()));
	}
