	@Test
	public void disconnectWithReceipt() throws Exception {
		logger.debug("Starting test disconnectWithReceipt()");

		MessageExchange connect = MessageExchangeBuilder.connect("sess1").build();
		this.relay.handleMessage(connect.message);
		this.responseHandler.expectMessages(connect);

		MessageExchange disconnect = MessageExchangeBuilder.disconnectWithReceipt("sess1", "r123").build();
		this.relay.handleMessage(disconnect.message);

		this.responseHandler.expectMessages(disconnect);
	}
