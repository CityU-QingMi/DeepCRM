	@Test
	public void sendMessageToController() throws Exception {
		TextMessage message = create(StompCommand.SEND).headers("destination:/app/simple").build();
		WebSocketSession session = doHandshake(new TestClientWebSocketHandler(0, message), "/ws").get();

		SimpleController controller = this.wac.getBean(SimpleController.class);
		try {
			assertTrue(controller.latch.await(TIMEOUT, TimeUnit.SECONDS));
		}
		finally {
			session.close();
		}
	}
