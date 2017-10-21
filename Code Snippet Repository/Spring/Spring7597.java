	@Test
	public void publishSubscribe() throws Exception {
		String destination = "/topic/foo";
		ConsumingHandler consumingHandler1 = new ConsumingHandler(destination);
		ListenableFuture<StompSession> consumerFuture1 = this.client.connect(consumingHandler1);

		ConsumingHandler consumingHandler2 = new ConsumingHandler(destination);
		ListenableFuture<StompSession> consumerFuture2 = this.client.connect(consumingHandler2);

		assertTrue(consumingHandler1.awaitForSubscriptions(5000));
		assertTrue(consumingHandler2.awaitForSubscriptions(5000));

		ProducingHandler producingHandler = new ProducingHandler();
		producingHandler.addToSend(destination, "foo1");
		producingHandler.addToSend(destination, "foo2");
		ListenableFuture<StompSession> producerFuture = this.client.connect(producingHandler);

		assertTrue(consumingHandler1.awaitForMessageCount(2, 5000));
		assertThat(consumingHandler1.getReceived(), containsInAnyOrder("foo1", "foo2"));

		assertTrue(consumingHandler2.awaitForMessageCount(2, 5000));
		assertThat(consumingHandler2.getReceived(), containsInAnyOrder("foo1", "foo2"));

		consumerFuture1.get().disconnect();
		consumerFuture2.get().disconnect();
		producerFuture.get().disconnect();
	}
