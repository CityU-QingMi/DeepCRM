	@Test
	public void sendMessageToControllerAndReceiveReplyViaTopic() throws Exception {
		TextMessage message1 = create(StompCommand.SUBSCRIBE)
				.headers("id:subs1", "destination:/topic/increment").build();
		TextMessage message2 = create(StompCommand.SEND)
				.headers("destination:/app/increment").body("5").build();

		TestClientWebSocketHandler clientHandler = new TestClientWebSocketHandler(1, message1, message2);
		WebSocketSession session = doHandshake(clientHandler, "/ws").get();

		try {
			assertTrue(clientHandler.latch.await(TIMEOUT, TimeUnit.SECONDS));
		}
		finally {
			session.close();
		}
	}
