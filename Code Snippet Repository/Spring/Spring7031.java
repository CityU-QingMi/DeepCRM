	@Test
	public void testThatAnExceptionThrownFromTheHandlingMethodIsSimplySwallowedByDefault() throws Exception {
		final IllegalArgumentException exception = new IllegalArgumentException();

		TextMessage textMessage = mock(TextMessage.class);
		MessageDelegate delegate = mock(MessageDelegate.class);
		willThrow(exception).given(delegate).handleMessage(textMessage);

		MessageListenerAdapter adapter = new MessageListenerAdapter(delegate) {
			@Override
			protected void handleListenerException(Throwable ex) {
				assertNotNull("The Throwable passed to the handleListenerException(..) method must never be null.", ex);
				assertTrue("The Throwable passed to the handleListenerException(..) method must be of type [ListenerExecutionFailedException].",
						ex instanceof ListenerExecutionFailedException);
				ListenerExecutionFailedException lefx = (ListenerExecutionFailedException) ex;
				Throwable cause = lefx.getCause();
				assertNotNull("The cause of a ListenerExecutionFailedException must be preserved.", cause);
				assertSame(exception, cause);
			}
		};
		// we DON'T want the default SimpleMessageConversion happening...
		adapter.setMessageConverter(null);
		adapter.onMessage(textMessage);
	}
