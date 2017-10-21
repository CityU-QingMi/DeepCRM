	@Test
	public void testWithResponsiveMessageDelegateNoDefaultDestination_SendsReturnTextMessageWhenSessionSupplied_AndSendingThrowsJMSException() throws Exception {
		Queue destination = mock(Queue.class);

		final TextMessage sentTextMessage = mock(TextMessage.class);
		// correlation ID is queried when response is being created...
		given(sentTextMessage.getJMSCorrelationID()).willReturn(CORRELATION_ID);
		// Reply-To is queried when response is being created...
		given(sentTextMessage.getJMSReplyTo()).willReturn(destination);

		TextMessage responseTextMessage = mock(TextMessage.class);
		MessageProducer messageProducer = mock(MessageProducer.class);
		willThrow(new JMSException("Doe!")).given(messageProducer).send(responseTextMessage);

		final QueueSession session = mock(QueueSession.class);
		given(session.createTextMessage(RESPONSE_TEXT)).willReturn(responseTextMessage);
		given(session.createProducer(destination)).willReturn(messageProducer);

		ResponsiveMessageDelegate delegate = mock(ResponsiveMessageDelegate.class);
		given(delegate.handleMessage(sentTextMessage)).willReturn(RESPONSE_TEXT);

		final MessageListenerAdapter adapter = new MessageListenerAdapter(delegate) {
			@Override
			protected Object extractMessage(Message message) {
				return message;
			}
		};
		try {
			adapter.onMessage(sentTextMessage, session);
			fail("expected CouldNotSendReplyException with JMSException");
		}
		catch (ReplyFailureException ex) {
			assertEquals(JMSException.class, ex.getCause().getClass());
		}

		verify(responseTextMessage).setJMSCorrelationID(CORRELATION_ID);
		verify(messageProducer).close();
		verify(delegate).handleMessage(sentTextMessage);
	}
