	@Test
	public void testWhenTheAdapterItselfIsTheDelegate() throws Exception {
		TextMessage textMessage = mock(TextMessage.class);
		// TextMessage contents must be unwrapped...
		given(textMessage.getText()).willReturn(TEXT);

		StubMessageListenerAdapter adapter = new StubMessageListenerAdapter();
		adapter.onMessage(textMessage);
		assertTrue(adapter.wasCalled());
	}
