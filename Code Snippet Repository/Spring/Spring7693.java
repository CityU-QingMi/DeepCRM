	@Test
	public void interceptorWithModifiedMessage() {
		Message<?> expected = mock(Message.class);
		BeforeHandleInterceptor interceptor = new BeforeHandleInterceptor();
		interceptor.setMessageToReturn(expected);
		this.channel.addInterceptor(interceptor);
		this.channel.subscribe(this.handler);
		this.channel.send(this.message);
		verify(this.handler).handleMessage(expected);
		assertEquals(1, interceptor.getCounter().get());
		assertTrue(interceptor.wasAfterHandledInvoked());
	}
