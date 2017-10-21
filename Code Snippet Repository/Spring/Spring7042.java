	@Test
	public void payloadConversionLazilyInvoked() throws JMSException {
		javax.jms.Message jmsMessage = mock(javax.jms.Message.class);
		MessageConverter messageConverter = mock(MessageConverter.class);
		given(messageConverter.fromMessage(jmsMessage)).willReturn("FooBar");
		MessagingMessageListenerAdapter listener = getSimpleInstance("simple", Message.class);
		listener.setMessageConverter(messageConverter);
		Message<?> message = listener.toMessagingMessage(jmsMessage);
		verify(messageConverter, never()).fromMessage(jmsMessage);
		assertEquals("FooBar", message.getPayload());
		verify(messageConverter, times(1)).fromMessage(jmsMessage);
	}
