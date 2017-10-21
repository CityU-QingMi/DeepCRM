	@Test
	public void success() throws Exception {
		final String s = "Hello World";
		Callable<String> callable = () -> s;

		ListenableFutureTask<String> task = new ListenableFutureTask<>(callable);
		task.addCallback(new ListenableFutureCallback<String>() {
			@Override
			public void onSuccess(String result) {
				assertEquals(s, result);
			}
			@Override
			public void onFailure(Throwable ex) {
				fail(ex.getMessage());
			}
		});
		task.run();

		assertSame(s, task.get());
		assertSame(s, task.completable().get());
		task.completable().thenAccept(v -> assertSame(s, v));
	}
