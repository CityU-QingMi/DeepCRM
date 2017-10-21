	@Test
	public void preSendInterceptorReturningModifiedMessage() {
		Message<?> expected = mock(Message.class);
		PreSendInterceptor interceptor = new PreSendInterceptor();
		interceptor.setMessageToReturn(expected);
		this.channel.addInterceptor(interceptor);
		this.channel.send(MessageBuilder.withPayload("test").build());

		assertEquals(1, this.messageHandler.getMessages().size());
		Message<?> result = this.messageHandler.getMessages().get(0);

		assertNotNull(result);
		assertSame(expected, result);
		assertTrue(interceptor.wasAfterCompletionInvoked());
	}
