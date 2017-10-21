	@Test
	public void testWithResponsiveMessageDelegate_DoesNotSendReturnTextMessageIfNoSessionSupplied() throws Exception {
		TextMessage textMessage = mock(TextMessage.class);
		ResponsiveMessageDelegate delegate = mock(ResponsiveMessageDelegate.class);
		given(delegate.handleMessage(textMessage)).willReturn(TEXT);

		MessageListenerAdapter adapter = new MessageListenerAdapter(delegate);
		// we DON'T want the default SimpleMessageConversion happening...
		adapter.setMessageConverter(null);
		adapter.onMessage(textMessage);
	}
