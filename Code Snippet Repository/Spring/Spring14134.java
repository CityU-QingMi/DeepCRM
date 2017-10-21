	@Test
	public void sendMessageToBrokerAndReceiveReplyViaTopic() throws Exception {
		TextMessage m1 = create(StompCommand.SUBSCRIBE).headers("id:subs1", "destination:/topic/foo").build();
		TextMessage m2 = create(StompCommand.SEND).headers("destination:/topic/foo").body("5").build();

		TestClientWebSocketHandler clientHandler = new TestClientWebSocketHandler(1, m1, m2);
		WebSocketSession session = doHandshake(clientHandler, "/ws").get();

		try {
			assertTrue(clientHandler.latch.await(TIMEOUT, TimeUnit.SECONDS));

			String payload = clientHandler.actual.get(0).getPayload();
			assertTrue("Expected STOMP MESSAGE, got " + payload, payload.startsWith("MESSAGE\n"));
		}
		finally {
			session.close();
		}
	}
