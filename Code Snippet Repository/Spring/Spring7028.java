	@Test
	public void testWithMessageDelegate() throws Exception {
		TextMessage textMessage = mock(TextMessage.class);

		MessageDelegate delegate = mock(MessageDelegate.class);

		MessageListenerAdapter adapter = new MessageListenerAdapter(delegate);
		// we DON'T want the default SimpleMessageConversion happening...
		adapter.setMessageConverter(null);
		adapter.onMessage(textMessage);

		verify(delegate).handleMessage(textMessage);
	}
