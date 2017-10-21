	@Test
	public void convertMessageNotWritableException() throws JMSException {
		Message<String> message = createTextMessage();
		MessageConverter messageConverter = mock(MessageConverter.class);
		willThrow(MessageNotWriteableException.class).given(messageConverter).toMessage(eq(message), any());
		this.messagingTemplate.setJmsMessageConverter(messageConverter);
		invokeMessageCreator();

		this.thrown.expect(org.springframework.messaging.converter.MessageConversionException.class);
		this.messagingTemplate.send("myQueue", message);
	}
