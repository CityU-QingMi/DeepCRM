	@Test
	public void afterCompletionWithSendException() {
		final AbstractMessageChannel testChannel = new AbstractMessageChannel() {
			@Override
			protected boolean sendInternal(Message<?> message, long timeout) {
				throw new RuntimeException("Simulated exception");
			}
		};
		PreSendInterceptor interceptor1 = new PreSendInterceptor();
		PreSendInterceptor interceptor2 = new PreSendInterceptor();
		testChannel.addInterceptor(interceptor1);
		testChannel.addInterceptor(interceptor2);
		try {
			testChannel.send(MessageBuilder.withPayload("test").build());
		}
		catch (Exception ex) {
			assertEquals("Simulated exception", ex.getCause().getMessage());
		}
		assertTrue(interceptor1.wasAfterCompletionInvoked());
		assertTrue(interceptor2.wasAfterCompletionInvoked());
	}
