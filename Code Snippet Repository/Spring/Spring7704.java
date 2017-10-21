	@Test
	public void getMutableAccessorNewInstanceMatchingType() {
		TestMessageHeaderAccessor expected = new TestMessageHeaderAccessor();
		Message<?> message = MessageBuilder.createMessage("payload", expected.getMessageHeaders());

		MessageHeaderAccessor actual = MessageHeaderAccessor.getMutableAccessor(message);
		assertNotNull(actual);
		assertTrue(actual.isMutable());
		assertEquals(TestMessageHeaderAccessor.class, actual.getClass());
	}
