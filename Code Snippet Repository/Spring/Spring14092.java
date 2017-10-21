	@Test
	public void sendAfterBlockedSend() throws IOException, InterruptedException {

		BlockingSession blockingSession = new BlockingSession();
		blockingSession.setOpen(true);
		CountDownLatch sentMessageLatch = blockingSession.getSentMessageLatch();

		final ConcurrentWebSocketSessionDecorator concurrentSession =
				new ConcurrentWebSocketSessionDecorator(blockingSession, 10 * 1000, 1024);

		Executors.newSingleThreadExecutor().submit((Runnable) () -> {
			TextMessage message = new TextMessage("slow message");
			try {
				concurrentSession.sendMessage(message);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		});

		assertTrue(sentMessageLatch.await(5, TimeUnit.SECONDS));

		// ensure some send time elapses
		Thread.sleep(100);
		assertTrue(concurrentSession.getTimeSinceSendStarted() > 0);

		TextMessage payload = new TextMessage("payload");
		for (int i = 0; i < 5; i++) {
			concurrentSession.sendMessage(payload);
		}

		assertTrue(concurrentSession.getTimeSinceSendStarted() > 0);
		assertEquals(5 * payload.getPayloadLength(), concurrentSession.getBufferSize());
		assertTrue(blockingSession.isOpen());
	}
