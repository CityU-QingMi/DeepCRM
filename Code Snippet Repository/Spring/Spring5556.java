	@Test
	public void failureWithLambdas() throws Exception {
		final String s = "Hello World";
		IOException ex = new IOException(s);
		Callable<String> callable = () -> {
			throw ex;
		};

		SuccessCallback<String> successCallback = mock(SuccessCallback.class);
		FailureCallback failureCallback = mock(FailureCallback.class);
		ListenableFutureTask<String> task = new ListenableFutureTask<>(callable);
		task.addCallback(successCallback, failureCallback);
		task.run();
		verify(failureCallback).onFailure(ex);
		verifyZeroInteractions(successCallback);

		try {
			task.get();
			fail("Should have thrown ExecutionException");
		}
		catch (ExecutionException ex2) {
			assertSame(s, ex2.getCause().getMessage());
		}
		try {
			task.completable().get();
			fail("Should have thrown ExecutionException");
		}
		catch (ExecutionException ex2) {
			assertSame(s, ex2.getCause().getMessage());
		}
	}
