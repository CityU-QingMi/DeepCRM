	@Test
	public void handleExceptionAndSendToUser() throws Exception {
		String destHeader = "destination:/user/queue/error";
		TextMessage m1 = create(StompCommand.SUBSCRIBE).headers("id:subs1", destHeader).build();
		TextMessage m2 = create(StompCommand.SEND).headers("destination:/app/exception").build();

		TestClientWebSocketHandler clientHandler = new TestClientWebSocketHandler(1, m1, m2);
		WebSocketSession session = doHandshake(clientHandler, "/ws").get();

		try {
			assertTrue(clientHandler.latch.await(TIMEOUT, TimeUnit.SECONDS));
			String payload = clientHandler.actual.get(0).getPayload();
			assertTrue(payload.startsWith("MESSAGE\n"));
			assertTrue(payload.contains("destination:/user/queue/error\n"));
			assertTrue(payload.endsWith("Got error: Bad input\0"));
		}
		finally {
			session.close();
		}
	}
