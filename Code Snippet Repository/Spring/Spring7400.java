	@Test
	public void sendAndReceiveTimeout() throws InterruptedException {
		final AtomicReference<Throwable> failure = new AtomicReference<Throwable>();
		final CountDownLatch latch = new CountDownLatch(1);

		this.template.setReceiveTimeout(1);
		this.template.setSendTimeout(30_000L);
		this.template.setThrowExceptionOnLateReply(true);

		SubscribableChannel channel = mock(SubscribableChannel.class);
		MessageHandler handler = createLateReplier(latch, failure);
		doAnswer(invocation -> {
			this.executor.execute(() -> {
				handler.handleMessage(invocation.getArgument(0));
			});
			return true;
		}).when(channel).send(any(Message.class), anyLong());

		assertNull(this.template.convertSendAndReceive(channel, "request", String.class));
		assertTrue(latch.await(10_000, TimeUnit.MILLISECONDS));

		Throwable ex = failure.get();
		if (ex != null) {
			throw new AssertionError(ex);
		}
		verify(channel).send(any(Message.class), eq(30_000L));
	}
