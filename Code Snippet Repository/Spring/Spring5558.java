	@Test
	public void getWithTimeoutThrowsTimeoutException() throws ExecutionException, InterruptedException {
		try {
			settableListenableFuture.get(1L, TimeUnit.MILLISECONDS);
			fail("Expected TimeoutException");
		}
		catch (TimeoutException ex) {
			// expected
		}
	}
