	@Test
	public void testWithMessageContentsDelegateForObjectMessageWithPlainObject() throws Exception {
		ObjectMessage objectMessage = mock(ObjectMessage.class);
		given(objectMessage.getObject()).willReturn(OBJECT);

		MessageContentsDelegate delegate = mock(MessageContentsDelegate.class);

		MessageListenerAdapter adapter = new MessageListenerAdapter(delegate);
		adapter.onMessage(objectMessage);

		verify(delegate).handleMessage(OBJECT);
	}
