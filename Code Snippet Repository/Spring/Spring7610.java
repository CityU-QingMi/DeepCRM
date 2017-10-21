	@Test
	public void publishSubscribe() throws Exception {
		logger.debug("Starting test publishSubscribe()");

		String sess1 = "sess1";
		String sess2 = "sess2";
		String subs1 = "subs1";
		String destination = "/topic/test";

		MessageExchange conn1 = MessageExchangeBuilder.connect(sess1).build();
		MessageExchange conn2 = MessageExchangeBuilder.connect(sess2).build();
		this.relay.handleMessage(conn1.message);
		this.relay.handleMessage(conn2.message);
		this.responseHandler.expectMessages(conn1, conn2);

		MessageExchange subscribe = MessageExchangeBuilder.subscribeWithReceipt(sess1, subs1, destination, "r1").build();
		this.relay.handleMessage(subscribe.message);
		this.responseHandler.expectMessages(subscribe);

		MessageExchange send = MessageExchangeBuilder.send(destination, "foo").andExpectMessage(sess1, subs1).build();
		this.relay.handleMessage(send.message);
		this.responseHandler.expectMessages(send);
	}
