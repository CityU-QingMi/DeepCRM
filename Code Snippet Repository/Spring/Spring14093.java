	@Test
	public void sendTimeLimitExceeded() throws IOException, InterruptedException {

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

		try {
			TextMessage payload = new TextMessage("payload");
			concurrentSession.sendMessage(payload);
			fail("Expected exception");
		}
		catch (SessionLimitExceededException ex) {
			String actual = ex.getMessage();
			String regex = "Message send time [\\d]+ \\(ms\\) for session '123' exceeded the allowed limit 100";
			assertTrue("Unexpected message: " + actual, actual.matches(regex));
			assertEquals(CloseStatus.SESSION_NOT_RELIABLE, ex.getStatus());
		}
	}
