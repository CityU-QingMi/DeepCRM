	@Test
	public void successWithLambdas() throws Exception {
		final String s = "Hello World";
		Callable<String> callable = () -> s;

		SuccessCallback<String> successCallback = mock(SuccessCallback.class);
		FailureCallback failureCallback = mock(FailureCallback.class);
		ListenableFutureTask<String> task = new ListenableFutureTask<>(callable);
		task.addCallback(successCallback, failureCallback);
		task.run();
		verify(successCallback).onSuccess(s);
		verifyZeroInteractions(failureCallback);

		assertSame(s, task.get());
		assertSame(s, task.completable().get());
		task.completable().thenAccept(v -> assertSame(s, v));
	}
