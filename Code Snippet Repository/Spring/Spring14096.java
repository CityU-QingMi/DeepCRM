	@Test
	public void closeStatusChangesToSessionNotReliable() throws Exception {

		BlockingSession blockingSession = new BlockingSession();
		blockingSession.setId("123");
		blockingSession.setOpen(true);
		CountDownLatch sentMessageLatch = blockingSession.getSentMessageLatch();

		int sendTimeLimit = 100;
		int bufferSizeLimit = 1024;

		final ConcurrentWebSocketSessionDecorator concurrentSession =
				new ConcurrentWebSocketSessionDecorator(blockingSession, sendTimeLimit, bufferSizeLimit);

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
		Thread.sleep(sendTimeLimit + 100);

		concurrentSession.close(CloseStatus.PROTOCOL_ERROR);

		assertEquals("CloseStatus should have changed to SESSION_NOT_RELIABLE",
				CloseStatus.SESSION_NOT_RELIABLE, blockingSession.getCloseStatus());
	}
