	@Test
	public void testWithResponsiveMessageDelegateDoesNotSendReturnTextMessageWhenSessionSupplied_AndListenerMethodThrowsException() throws Exception {
		final TextMessage message = mock(TextMessage.class);
		final QueueSession session = mock(QueueSession.class);

		ResponsiveMessageDelegate delegate = mock(ResponsiveMessageDelegate.class);
		willThrow(new IllegalArgumentException("Doe!")).given(delegate).handleMessage(message);

		final MessageListenerAdapter adapter = new MessageListenerAdapter(delegate) {
			@Override
			protected Object extractMessage(Message message) {
				return message;
			}
		};
		try {
			adapter.onMessage(message, session);
			fail("expected ListenerExecutionFailedException");
		}
		catch (ListenerExecutionFailedException ex) { /* expected */ }
	}
