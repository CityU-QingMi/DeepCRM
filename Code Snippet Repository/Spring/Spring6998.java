	protected void doTestJmsException(JMSException original, Class<? extends JmsException> thrownExceptionClass) throws Exception {
		JmsTemplate template = createTemplate();
		template.setConnectionFactory(this.connectionFactory);
		template.setMessageConverter(new SimpleMessageConverter());
		String s = "Hello world";

		MessageProducer messageProducer = mock(MessageProducer.class);
		TextMessage textMessage = mock(TextMessage.class);

		reset(this.session);
		given(this.session.createProducer(this.queue)).willReturn(messageProducer);
		given(this.session.createTextMessage("Hello world")).willReturn(textMessage);

		willThrow(original).given(messageProducer).send(textMessage);

		try {
			template.convertAndSend(this.queue, s);
			fail("Should have thrown JmsException");
		}
		catch (JmsException wrappedEx) {
			// expected
			assertEquals(thrownExceptionClass, wrappedEx.getClass());
			assertEquals(original, wrappedEx.getCause());
		}

		verify(messageProducer).close();
		verify(this.session).close();
		verify(this.connection).close();
	}
