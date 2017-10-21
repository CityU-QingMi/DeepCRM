	@Test
	public void asyncResultWithCallbackAndException() throws Exception {
		IOException ex = new IOException();
		final Set<Throwable> values = new HashSet<>(1);
		ListenableFuture<String> future = AsyncResult.forExecutionException(ex);
		future.addCallback(new ListenableFutureCallback<String>() {
			@Override
			public void onSuccess(String result) {
				fail("Success callback not expected: " + result);
			}
			@Override
			public void onFailure(Throwable ex) {
				values.add(ex);
			}
		});
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
