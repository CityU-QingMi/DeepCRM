	@Test
	public void afterCompletionWithPreSendException() {
		PreSendInterceptor interceptor1 = new PreSendInterceptor();
		PreSendInterceptor interceptor2 = new PreSendInterceptor();
		interceptor2.setExceptionToRaise(new RuntimeException("Simulated exception"));
		this.channel.addInterceptor(interceptor1);
		this.channel.addInterceptor(interceptor2);
		try {
			this.channel.send(MessageBuilder.withPayload("test").build());
		}
		catch (Exception ex) {
			assertEquals("Simulated exception", ex.getCause().getMessage());
		}
		assertTrue(interceptor1.wasAfterCompletionInvoked());
		assertFalse(interceptor2.wasAfterCompletionInvoked());
	}
