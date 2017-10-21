	@Test
	public void testProducerCallbackWithIdAndTimestampDisabled() throws Exception {
		JmsTemplate template = createTemplate();
		template.setConnectionFactory(this.connectionFactory);
		template.setMessageIdEnabled(false);
		template.setMessageTimestampEnabled(false);

		MessageProducer messageProducer = mock(MessageProducer.class);
		given(this.session.createProducer(null)).willReturn(messageProducer);
		given(messageProducer.getPriority()).willReturn(4);

		template.execute((ProducerCallback<Void>) (session1, producer) -> {
			session1.getTransacted();
			producer.getPriority();
			return null;
		});

		verify(messageProducer).setDisableMessageID(true);
		verify(messageProducer).setDisableMessageTimestamp(true);
		verify(messageProducer).close();
		verify(this.session).close();
		verify(this.connection).close();
	}
