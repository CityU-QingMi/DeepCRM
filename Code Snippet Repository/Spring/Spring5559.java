	@Test
	public void getWithTimeoutWaitsForCompletion() throws ExecutionException, InterruptedException, TimeoutException {
		final String string = "hello";

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(20L);
					settableListenableFuture.set(string);
				}
				catch (InterruptedException ex) {
					throw new RuntimeException(ex);
				}
			}
		}).start();

		String value = settableListenableFuture.get(500L, TimeUnit.MILLISECONDS);
		assertThat(value, equalTo(string));
		assertFalse(settableListenableFuture.isCancelled());
		assertTrue(settableListenableFuture.isDone());
	}
