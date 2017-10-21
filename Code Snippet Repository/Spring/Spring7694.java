	@Test
	public void interceptorWithNull() {
		BeforeHandleInterceptor interceptor1 = new BeforeHandleInterceptor();
		NullReturningBeforeHandleInterceptor interceptor2 = new NullReturningBeforeHandleInterceptor();
		this.channel.addInterceptor(interceptor1);
		this.channel.addInterceptor(interceptor2);
		this.channel.subscribe(this.handler);
		this.channel.send(this.message);
		verifyNoMoreInteractions(this.handler);
		assertEquals(1, interceptor1.getCounter().get());
		assertEquals(1, interceptor2.getCounter().get());
		assertTrue(interceptor1.wasAfterHandledInvoked());
	}
