	@Test
	public void testWithResponsiveMessageDelegateNoDefaultDestination_SendsReturnTextMessageWhenSessionSupplied() throws Exception {
		Queue destination = mock(Queue.class);
		TextMessage sentTextMessage = mock(TextMessage.class);
		// correlation ID is queried when response is being created...
		given(sentTextMessage.getJMSCorrelationID()).willReturn(null);
		given(sentTextMessage.getJMSMessageID()).willReturn(CORRELATION_ID);
		// Reply-To is queried when response is being created...
		given(sentTextMessage.getJMSReplyTo()).willReturn(destination);

		TextMessage responseTextMessage = mock(TextMessage.class);
		MessageProducer messageProducer = mock(MessageProducer.class);
		Session session = mock(Session.class);
		given(session.createTextMessage(RESPONSE_TEXT)).willReturn(responseTextMessage);
		given(session.createProducer(destination)).willReturn(messageProducer);

		ResponsiveMessageDelegate delegate = mock(ResponsiveMessageDelegate.class);
		given(delegate.handleMessage(sentTextMessage)).willReturn(RESPONSE_TEXT);

		MessageListenerAdapter adapter = new MessageListenerAdapter(delegate) {
			@Override
			protected Object extractMessage(Message message) {
				return message;
			}
		};
		adapter.onMessage(sentTextMessage, session);

		verify(responseTextMessage).setJMSCorrelationID(CORRELATION_ID);
		verify(messageProducer).send(responseTextMessage);
		verify(messageProducer).close();
		verify(delegate).handleMessage(sentTextMessage);
	}
