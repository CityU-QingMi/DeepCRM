	@Test
	public void testWithMessageContentsDelegateForTextMessage() throws Exception {
		TextMessage textMessage = mock(TextMessage.class);
		// TextMessage contents must be unwrapped...
		given(textMessage.getText()).willReturn(TEXT);

		MessageContentsDelegate delegate = mock(MessageContentsDelegate.class);

		MessageListenerAdapter adapter = new MessageListenerAdapter(delegate);
		adapter.onMessage(textMessage);

		verify(delegate).handleMessage(TEXT);
	}
