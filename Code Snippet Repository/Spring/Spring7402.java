	@Test
	public void sendAndReceiveVariableTimeoutCustomHeaders() throws InterruptedException {
		final AtomicReference<Throwable> failure = new AtomicReference<Throwable>();
		final CountDownLatch latch = new CountDownLatch(1);

		this.template.setSendTimeout(20_000);
		this.template.setReceiveTimeout(10_000);
		this.template.setThrowExceptionOnLateReply(true);
		this.template.setSendTimeoutHeader("sto");
		this.template.setReceiveTimeoutHeader("rto");

		SubscribableChannel channel = mock(SubscribableChannel.class);
		MessageHandler handler = createLateReplier(latch, failure);
		doAnswer(invocation -> {
			this.executor.execute(() -> {
				handler.handleMessage(invocation.getArgument(0));
			});
			return true;
		}).when(channel).send(any(Message.class), anyLong());

		Message<?> message = MessageBuilder.withPayload("request")
				.setHeader("sto", 30_000L)
				.setHeader("rto", 1L)
				.build();
		assertNull(this.template.sendAndReceive(channel, message));
		assertTrue(latch.await(10_000, TimeUnit.MILLISECONDS));

		Throwable ex = failure.get();
		if (ex != null) {
			throw new AssertionError(ex);
		}
		verify(channel).send(any(Message.class), eq(30_000L));
	}
