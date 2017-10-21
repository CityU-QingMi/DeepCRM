	@Test
	public void postSendInterceptorMessageWasSent() {
		final AtomicBoolean preSendInvoked = new AtomicBoolean(false);
		final AtomicBoolean completionInvoked = new AtomicBoolean(false);
		this.channel.addInterceptor(new ChannelInterceptorAdapter() {
			@Override
			public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
				assertInput(message, channel, sent);
				preSendInvoked.set(true);
			}
			@Override
			public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
				assertInput(message, channel, sent);
				completionInvoked.set(true);
			}
			private void assertInput(Message<?> message, MessageChannel channel, boolean sent) {
				assertNotNull(message);
				assertNotNull(channel);
				assertSame(ChannelInterceptorTests.this.channel, channel);
				assertTrue(sent);
			}
		});
		this.channel.send(MessageBuilder.withPayload("test").build());
		assertTrue(preSendInvoked.get());
		assertTrue(completionInvoked.get());
	}
