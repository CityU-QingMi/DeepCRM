	@Test
	public void asyncResultWithSeparateCallbacksAndException() throws Exception {
		IOException ex = new IOException();
		final Set<Throwable> values = new HashSet<>(1);
		ListenableFuture<String> future = AsyncResult.forExecutionException(ex);
		future.addCallback((result) -> fail("Success callback not expected: " + result), values::add);
		assertSame(ex, values.iterator().next());
		try {
			future.get();
			fail("Should have thrown ExecutionException");
		}
		catch (ExecutionException ex2) {
			assertSame(ex, ex2.getCause());
		}
		try {
			future.completable().get();
			fail("Should have thrown ExecutionException");
		}
		catch (ExecutionException ex2) {
			assertSame(ex, ex2.getCause());
		}
	}
