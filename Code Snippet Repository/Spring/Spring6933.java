	@Test
	public void resolveCustomHeaderNameAndPayload() throws JMSException {
		MessagingMessageListenerAdapter listener = createDefaultInstance(String.class, int.class);

		Session session = mock(Session.class);
		StubTextMessage message = createSimpleJmsTextMessage("my payload");
		message.setIntProperty("myCounter", 24);
		listener.onMessage(message, session);
		assertDefaultListenerMethodInvocation();
	}
