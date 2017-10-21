	@Test
	public void sendSubscribeToControllerAndReceiveReply() throws Exception {
		String destHeader = "destination:/app/number";
		TextMessage message = create(StompCommand.SUBSCRIBE).headers("id:subs1", destHeader).build();

		TestClientWebSocketHandler clientHandler = new TestClientWebSocketHandler(1, message);
		WebSocketSession session = doHandshake(clientHandler, "/ws").get();

		try {
			assertTrue(clientHandler.latch.await(TIMEOUT, TimeUnit.SECONDS));
			String payload = clientHandler.actual.get(0).getPayload();
			assertTrue("Expected STOMP destination=/app/number, got " + payload, payload.contains(destHeader));
			assertTrue("Expected STOMP Payload=42, got " + payload, payload.contains("42"));
		}
		finally {
			session.close();
		}
	}
