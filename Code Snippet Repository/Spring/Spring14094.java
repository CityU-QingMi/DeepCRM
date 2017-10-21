	@Test
	public void sendBufferSizeExceeded() throws IOException, InterruptedException {

		BlockingSession blockingSession = new BlockingSession();
		blockingSession.setId("123");
		blockingSession.setOpen(true);
		CountDownLatch sentMessageLatch = blockingSession.getSentMessageLatch();

		int sendTimeLimit = 10 * 1000;
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

		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i < 1023; i++) {
			sb.append("a");
		}

		TextMessage message = new TextMessage(sb.toString());
		concurrentSession.sendMessage(message);

		assertEquals(1023, concurrentSession.getBufferSize());
		assertTrue(blockingSession.isOpen());

		try {
			concurrentSession.sendMessage(message);
			fail("Expected exception");
		}
		catch (SessionLimitExceededException ex) {
			String actual = ex.getMessage();
			String regex = "The send buffer size [\\d]+ bytes for session '123' exceeded the allowed limit 1024";
			assertTrue("Unexpected message: " + actual, actual.matches(regex));
			assertEquals(CloseStatus.SESSION_NOT_RELIABLE, ex.getStatus());
		}
	}
