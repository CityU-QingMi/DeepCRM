	@Test
	public void incomingMessageUsesMessageConverter() throws JMSException {
		javax.jms.Message jmsMessage = mock(javax.jms.Message.class);
		Session session = mock(Session.class);
		MessageConverter messageConverter = mock(MessageConverter.class);
		given(messageConverter.fromMessage(jmsMessage)).willReturn("FooBar");
		MessagingMessageListenerAdapter listener = getSimpleInstance("simple", Message.class);
		listener.setMessageConverter(messageConverter);
		listener.onMessage(jmsMessage, session);
		verify(messageConverter, times(1)).fromMessage(jmsMessage);
		assertEquals(1, sample.simples.size());
		assertEquals("FooBar", sample.simples.get(0).getPayload());
	}
