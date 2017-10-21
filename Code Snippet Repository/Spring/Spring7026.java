	@Test
	public void testWithMessageContentsDelegateForObjectMessage() throws Exception {
		ObjectMessage objectMessage = mock(ObjectMessage.class);
		given(objectMessage.getObject()).willReturn(NUMBER);

		MessageContentsDelegate delegate = mock(MessageContentsDelegate.class);

		MessageListenerAdapter adapter = new MessageListenerAdapter(delegate);
		adapter.onMessage(objectMessage);

		verify(delegate).handleMessage(NUMBER);
	}
