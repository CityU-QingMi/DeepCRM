	@Test
	public void testRainyDayWithNoApplicableHandlingMethods() throws Exception {
		TextMessage textMessage = mock(TextMessage.class);
		// TextMessage contents must be unwrapped...
		given(textMessage.getText()).willReturn(TEXT);

		StubMessageListenerAdapter adapter = new StubMessageListenerAdapter();
		adapter.setDefaultListenerMethod("walnutsRock");
		adapter.onMessage(textMessage);
		assertFalse(adapter.wasCalled());
	}
