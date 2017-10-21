	@Test
	public void cancelStateThrowsExceptionWhenCallingGet() throws ExecutionException, InterruptedException {
		settableListenableFuture.cancel(true);

		try {
			settableListenableFuture.get();
			fail("Expected CancellationException");
		}
		catch (CancellationException ex) {
			// expected
		}

		assertTrue(settableListenableFuture.isCancelled());
		assertTrue(settableListenableFuture.isDone());
	}
