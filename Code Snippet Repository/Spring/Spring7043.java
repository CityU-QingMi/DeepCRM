	@Test
	public void headerConversionLazilyInvoked() throws JMSException {
		javax.jms.Message jmsMessage = mock(javax.jms.Message.class);
		when(jmsMessage.getPropertyNames()).thenThrow(new IllegalArgumentException("Header failure"));
		MessagingMessageListenerAdapter listener = getSimpleInstance("simple", Message.class);
		Message<?> message = listener.toMessagingMessage(jmsMessage);

		this.thrown.expect(IllegalArgumentException.class);
		this.thrown.expectMessage("Header failure");
		message.getHeaders(); // Triggers headers resolution
	}
