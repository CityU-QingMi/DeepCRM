	@Test
	public void testConverter() throws Exception {
		JmsTemplate template = createTemplate();
		template.setConnectionFactory(this.connectionFactory);
		template.setMessageConverter(new SimpleMessageConverter());
		String s = "Hello world";

		MessageProducer messageProducer = mock(MessageProducer.class);
		TextMessage textMessage = mock(TextMessage.class);

		given(this.session.createProducer(this.queue)).willReturn(messageProducer);
		given(this.session.createTextMessage("Hello world")).willReturn(textMessage);

		template.convertAndSend(this.queue, s);

		verify(messageProducer).send(textMessage);
		verify(messageProducer).close();
		if (useTransactedTemplate()) {
			verify(this.session).commit();
		}
		verify(this.session).close();
		verify(this.connection).close();
	}
