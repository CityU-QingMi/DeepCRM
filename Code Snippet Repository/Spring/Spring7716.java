	@Test
	public void getMutableAccessorSameInstance() {
		TestMessageHeaderAccessor expected = new TestMessageHeaderAccessor();
		expected.setLeaveMutable(true);
		Message<?> message = MessageBuilder.createMessage("payload", expected.getMessageHeaders());

		MessageHeaderAccessor actual = MessageHeaderAccessor.getMutableAccessor(message);
		assertNotNull(actual);
		assertTrue(actual.isMutable());
		assertSame(expected, actual);
	}
