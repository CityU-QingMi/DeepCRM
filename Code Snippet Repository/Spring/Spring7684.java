	@Test
	public void preSendInterceptorReturningNull() {
		PreSendInterceptor interceptor1 = new PreSendInterceptor();
		NullReturningPreSendInterceptor interceptor2 = new NullReturningPreSendInterceptor();
		this.channel.addInterceptor(interceptor1);
		this.channel.addInterceptor(interceptor2);
		Message<?> message = MessageBuilder.withPayload("test").build();
		this.channel.send(message);

		assertEquals(1, interceptor1.getCounter().get());
		assertEquals(1, interceptor2.getCounter().get());
		assertEquals(0, this.messageHandler.getMessages().size());
		assertTrue(interceptor1.wasAfterCompletionInvoked());
		assertFalse(interceptor2.wasAfterCompletionInvoked());
	}
