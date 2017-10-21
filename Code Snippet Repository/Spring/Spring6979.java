	@Test
	public void convertMessageConversionExceptionOnReceive() throws JMSException {
		javax.jms.Message message = createJmsTextMessage();
		MessageConverter messageConverter = mock(MessageConverter.class);
		willThrow(org.springframework.jms.support.converter.MessageConversionException.class)
				.given(messageConverter).fromMessage(message);
		this.messagingTemplate.setJmsMessageConverter(messageConverter);
		given(this.jmsTemplate.receive("myQueue")).willReturn(message);

		this.thrown.expect(org.springframework.messaging.converter.MessageConversionException.class);
		this.messagingTemplate.receive("myQueue");
	}
