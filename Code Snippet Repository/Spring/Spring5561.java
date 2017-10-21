	@Test
	public void cancelStateThrowsExceptionWhenCallingGetWithTimeout() throws ExecutionException, TimeoutException, InterruptedException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(20L);
					settableListenableFuture.cancel(true);
				}
				catch (InterruptedException ex) {
					throw new RuntimeException(ex);
				}
			}
		}).start();

		try {
			settableListenableFuture.get(500L, TimeUnit.MILLISECONDS);
			fail("Expected CancellationException");
		}
		catch (CancellationException ex) {
			// expected
		}

		assertTrue(settableListenableFuture.isCancelled());
		assertTrue(settableListenableFuture.isDone());
	}
