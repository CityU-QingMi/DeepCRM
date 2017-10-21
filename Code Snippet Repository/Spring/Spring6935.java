	@Test
	public void resolveHeaders() throws JMSException {
		MessagingMessageListenerAdapter listener = createDefaultInstance(String.class, Map.class);

		Session session = mock(Session.class);
		StubTextMessage message = createSimpleJmsTextMessage("my payload");
		message.setIntProperty("customInt", 1234);
		message.setJMSMessageID("abcd-1234");
		listener.onMessage(message, session);
		assertDefaultListenerMethodInvocation();
	}
