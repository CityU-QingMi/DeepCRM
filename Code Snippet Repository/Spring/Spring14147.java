	@Test
	public void publishSubscribe() throws Exception {

		String url = "ws://127.0.0.1:" + this.server.getPort() + "/stomp";

		TestHandler testHandler = new TestHandler("/topic/foo", "payload");
		this.stompClient.connect(url, testHandler);

		assertTrue(testHandler.awaitForMessageCount(1, 5000));
		assertThat(testHandler.getReceived(), containsInAnyOrder("payload"));
	}
