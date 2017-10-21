	@Test
	public void failure() throws Exception {
		final String s = "Hello World";
		Callable<String> callable = () -> {
			throw new IOException(s);
		};

		ListenableFutureTask<String> task = new ListenableFutureTask<>(callable);
		task.addCallback(new ListenableFutureCallback<String>() {
			@Override
			public void onSuccess(String result) {
				fail("onSuccess not expected");
			}
			@Override
			public void onFailure(Throwable ex) {
				assertEquals(s, ex.getMessage());
			}
		});
		task.run();

		try {
			task.get();
			fail("Should have thrown ExecutionException");
		}
		catch (ExecutionException ex) {
			assertSame(s, ex.getCause().getMessage());
		}
		try {
			task.completable().get();
			fail("Should have thrown ExecutionException");
		}
		catch (ExecutionException ex) {
			assertSame(s, ex.getCause().getMessage());
		}
	}
