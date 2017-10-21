	@Test
	public void resolveHeaderAndPayload() throws JMSException {
		MessagingMessageListenerAdapter listener = createDefaultInstance(String.class, int.class);

		Session session = mock(Session.class);
		StubTextMessage message = createSimpleJmsTextMessage("my payload");
		message.setIntProperty("myCounter", 55);
		listener.onMessage(message, session);
		assertDefaultListenerMethodInvocation();
	}
