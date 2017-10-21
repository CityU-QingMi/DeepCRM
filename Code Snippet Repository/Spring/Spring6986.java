	@Test
	public void convertAndSendCustomJmsMessageConverter() throws JMSException {
		this.messagingTemplate.setJmsMessageConverter(new SimpleMessageConverter() {
			@Override
			public javax.jms.Message toMessage(Object object, Session session)
					throws JMSException, org.springframework.jms.support.converter.MessageConversionException {
				throw new org.springframework.jms.support.converter.MessageConversionException("Test exception");
			}
		});

		this.messagingTemplate.convertAndSend("myQueue", "msg to convert");
		verify(this.jmsTemplate).send(eq("myQueue"), this.messageCreator.capture());

		this.thrown.expect(org.springframework.messaging.converter.MessageConversionException.class);
		this.thrown.expectMessage(new StringContains("Test exception"));
		this.messageCreator.getValue().createMessage(mock(Session.class));
	}
