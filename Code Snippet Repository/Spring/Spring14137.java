	@Test
	public void webSocketScope() throws Exception {
		TextMessage message1 = create(StompCommand.SUBSCRIBE)
				.headers("id:subs1", "destination:/topic/scopedBeanValue").build();
		TextMessage message2 = create(StompCommand.SEND)
				.headers("destination:/app/scopedBeanValue").build();

		TestClientWebSocketHandler clientHandler = new TestClientWebSocketHandler(1, message1, message2);
		WebSocketSession session = doHandshake(clientHandler, "/ws").get();

		try {
			assertTrue(clientHandler.latch.await(TIMEOUT, TimeUnit.SECONDS));
			String payload = clientHandler.actual.get(0).getPayload();
			assertTrue(payload.startsWith("MESSAGE\n"));
			assertTrue(payload.contains("destination:/topic/scopedBeanValue\n"));
			assertTrue(payload.endsWith("55\0"));
		}
		finally {
			session.close();
		}
	}
