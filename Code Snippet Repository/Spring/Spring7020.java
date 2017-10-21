	@Test
	public void testWithResponsiveMessageDelegateNoDefaultDestinationAndNoReplyToDestination_SendsReturnTextMessageWhenSessionSupplied() throws Exception {
		final TextMessage sentTextMessage = mock(TextMessage.class);
		// correlation ID is queried when response is being created...
		given(sentTextMessage.getJMSCorrelationID()).willReturn(CORRELATION_ID);
		// Reply-To is queried when response is being created...
		given(sentTextMessage.getJMSReplyTo()).willReturn(null);

		TextMessage responseTextMessage = mock(TextMessage.class);
		final QueueSession session = mock(QueueSession.class);
		given(session.createTextMessage(RESPONSE_TEXT)).willReturn(responseTextMessage);

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
			fail("expected CouldNotSendReplyException with InvalidDestinationException");
		}
		catch (ReplyFailureException ex) {
			assertEquals(InvalidDestinationException.class, ex.getCause().getClass());
		}

		verify(responseTextMessage).setJMSCorrelationID(CORRELATION_ID);
		verify(delegate).handleMessage(sentTextMessage);
	}
