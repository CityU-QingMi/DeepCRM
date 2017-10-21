	@Test
	public void buildMessageWithStandardMessage() throws JMSException {
		Destination replyTo = new Destination() {};
		Message<String> result = MessageBuilder.withPayload("Response")
				.setHeader("foo", "bar")
				.setHeader(JmsHeaders.TYPE, "msg_type")
				.setHeader(JmsHeaders.REPLY_TO, replyTo)
				.build();

		Session session = mock(Session.class);
		given(session.createTextMessage("Response")).willReturn(new StubTextMessage("Response"));
		MessagingMessageListenerAdapter listener = getSimpleInstance("echo", Message.class);
		javax.jms.Message replyMessage = listener.buildMessage(session, result);

		verify(session).createTextMessage("Response");
		assertNotNull("reply should never be null", replyMessage);
		assertEquals("Response", ((TextMessage) replyMessage).getText());
		assertEquals("custom header not copied", "bar", replyMessage.getStringProperty("foo"));
		assertEquals("type header not copied", "msg_type", replyMessage.getJMSType());
		assertEquals("replyTo header not copied", replyTo, replyMessage.getJMSReplyTo());
	}
