	@Test
	public void sendWithoutExecutor() {
		BeforeHandleInterceptor interceptor = new BeforeHandleInterceptor();
		this.channel.addInterceptor(interceptor);
		this.channel.subscribe(this.handler);
		this.channel.send(this.message);
		verify(this.handler).handleMessage(this.message);
		assertEquals(1, interceptor.getCounter().get());
		assertTrue(interceptor.wasAfterHandledInvoked());
	}
