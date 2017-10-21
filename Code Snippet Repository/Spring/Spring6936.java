	@Test
	public void resolveMessageHeaders() throws JMSException {
		MessagingMessageListenerAdapter listener = createDefaultInstance(MessageHeaders.class);

		Session session = mock(Session.class);
		StubTextMessage message = createSimpleJmsTextMessage("my payload");
		message.setLongProperty("customLong", 4567L);
		message.setJMSType("myMessageType");
		listener.onMessage(message, session);
		assertDefaultListenerMethodInvocation();
	}
