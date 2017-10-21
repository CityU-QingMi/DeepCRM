	@Test
	public void testWithResponsiveMessageDelegateWithDefaultDestination_SendsReturnTextMessageWhenSessionSupplied() throws Exception {
		Queue destination = mock(Queue.class);
		TextMessage sentTextMessage = mock(TextMessage.class);
		// correlation ID is queried when response is being created...
		given(sentTextMessage.getJMSCorrelationID()).willReturn(
				CORRELATION_ID);
		// Reply-To is queried when response is being created...
		given(sentTextMessage.getJMSReplyTo()).willReturn(null); // we want to fall back to the default...

		TextMessage responseTextMessage = mock(TextMessage.class);

		QueueSender queueSender = mock(QueueSender.class);
		Session session = mock(Session.class);
		given(session.createTextMessage(RESPONSE_TEXT)).willReturn(responseTextMessage);
		given(session.createProducer(destination)).willReturn(queueSender);

		ResponsiveMessageDelegate delegate = mock(ResponsiveMessageDelegate.class);
		given(delegate.handleMessage(sentTextMessage)).willReturn(RESPONSE_TEXT);

		MessageListenerAdapter adapter = new MessageListenerAdapter(delegate) {
			@Override
			protected Object extractMessage(Message message) {
				return message;
			}
		};
		adapter.setDefaultResponseDestination(destination);
		adapter.onMessage(sentTextMessage, session);

		verify(responseTextMessage).setJMSCorrelationID(CORRELATION_ID);
		verify(queueSender).send(responseTextMessage);
		verify(queueSender).close();
		verify(delegate).handleMessage(sentTextMessage);
	}
