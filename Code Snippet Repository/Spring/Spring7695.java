	@Test
	public void interceptorWithException() {
		IllegalStateException expected = new IllegalStateException("Fake exception");
		willThrow(expected).given(this.handler).handleMessage(this.message);
		BeforeHandleInterceptor interceptor = new BeforeHandleInterceptor();
		this.channel.addInterceptor(interceptor);
		this.channel.subscribe(this.handler);
		try {
			this.channel.send(this.message);
		}
		catch (MessageDeliveryException actual) {
			assertSame(expected, actual.getCause());
		}
		verify(this.handler).handleMessage(this.message);
		assertEquals(1, interceptor.getCounter().get());
		assertTrue(interceptor.wasAfterHandledInvoked());
	}
