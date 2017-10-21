	@Test
	public void testWithResponsiveMessageDelegateWhenReturnTypeIsAJMSMessageAndNoMessageConverterIsSupplied() throws Exception {
		Queue destination = mock(Queue.class);
		final TextMessage sentTextMessage = mock(TextMessage.class);
		// correlation ID is queried when response is being created...
		given(sentTextMessage.getJMSCorrelationID()).willReturn(CORRELATION_ID);
		// Reply-To is queried when response is being created...
		given(sentTextMessage.getJMSReplyTo()).willReturn(destination);

		TextMessage responseMessage = mock(TextMessage.class);
		QueueSender queueSender = mock(QueueSender.class);

		Session session = mock(Session.class);
		given(session.createProducer(destination)).willReturn(queueSender);

		ResponsiveJmsTextMessageReturningMessageDelegate delegate = mock(ResponsiveJmsTextMessageReturningMessageDelegate.class);
		given(delegate.handleMessage(sentTextMessage)).willReturn(responseMessage);

		final MessageListenerAdapter adapter = new MessageListenerAdapter(delegate) {
			@Override
			protected Object extractMessage(Message message) {
				return message;
			}
		};
		adapter.setMessageConverter(null);
		adapter.onMessage(sentTextMessage, session);

		verify(responseMessage).setJMSCorrelationID(CORRELATION_ID);
		verify(queueSender).send(responseMessage);
		verify(queueSender).close();
	}
