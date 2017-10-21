	@Test
	public void replyUsesMessageConverterForPayload() throws JMSException {
		Session session = mock(Session.class);
		MessageConverter messageConverter = mock(MessageConverter.class);
		given(messageConverter.toMessage("Response", session)).willReturn(new StubTextMessage("Response"));

		Message<String> result = MessageBuilder.withPayload("Response").build();
		MessagingMessageListenerAdapter listener = getSimpleInstance("echo", Message.class);
		listener.setMessageConverter(messageConverter);
		javax.jms.Message replyMessage = listener.buildMessage(session, result);

		verify(messageConverter, times(1)).toMessage("Response", session);
		assertNotNull("reply should never be null", replyMessage);
		assertEquals("Response", ((TextMessage) replyMessage).getText());
	}
