	@Test
	public void customConverterAlwaysTakesPrecedence() {
		MessageConverter messageConverter = mock(MessageConverter.class);
		given(this.jmsTemplate.getMessageConverter()).willReturn(messageConverter);
		MessageConverter customMessageConverter = mock(MessageConverter.class);
		JmsMessagingTemplate messagingTemplate = new JmsMessagingTemplate();
		messagingTemplate.setJmsMessageConverter(
				new MessagingMessageConverter(customMessageConverter));
		messagingTemplate.setJmsTemplate(this.jmsTemplate);
		messagingTemplate.afterPropertiesSet();
		assertPayloadConverter(messagingTemplate, customMessageConverter);
	}
