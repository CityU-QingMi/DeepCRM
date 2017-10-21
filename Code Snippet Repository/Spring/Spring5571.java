	@Test
	public void getWaitsForCompletion() throws ExecutionException, InterruptedException {
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

		String value = settableListenableFuture.get();
		assertThat(value, equalTo(string));
		assertFalse(settableListenableFuture.isCancelled());
		assertTrue(settableListenableFuture.isDone());
	}
