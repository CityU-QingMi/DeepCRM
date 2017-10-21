	@Test
	public void resolveJmsMessageHeaderAccessor() throws JMSException {
		MessagingMessageListenerAdapter listener = createDefaultInstance(JmsMessageHeaderAccessor.class);

		Session session = mock(Session.class);
		StubTextMessage message = createSimpleJmsTextMessage("my payload");
		message.setBooleanProperty("customBoolean", true);
		message.setJMSPriority(9);
		listener.onMessage(message, session);
		assertDefaultListenerMethodInvocation();
	}
