	@Test
	public void send() throws IOException {

		TestWebSocketSession session = new TestWebSocketSession();
		session.setOpen(true);

		ConcurrentWebSocketSessionDecorator concurrentSession =
				new ConcurrentWebSocketSessionDecorator(session, 1000, 1024);

		TextMessage textMessage = new TextMessage("payload");
		concurrentSession.sendMessage(textMessage);

		assertEquals(1, session.getSentMessages().size());
		assertEquals(textMessage, session.getSentMessages().get(0));

		assertEquals(0, concurrentSession.getBufferSize());
		assertEquals(0, concurrentSession.getTimeSinceSendStarted());
		assertTrue(session.isOpen());
	}
