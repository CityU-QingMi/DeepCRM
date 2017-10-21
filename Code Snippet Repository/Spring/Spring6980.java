	@Test
	public void convertMessageFormatException() throws JMSException {
		Message<String> message = createTextMessage();
		MessageConverter messageConverter = mock(MessageConverter.class);
		willThrow(MessageFormatException.class).given(messageConverter).toMessage(eq(message), any());
		this.messagingTemplate.setJmsMessageConverter(messageConverter);
		invokeMessageCreator();

		this.thrown.expect(org.springframework.messaging.converter.MessageConversionException.class);
		this.messagingTemplate.send("myQueue", message);
	}
