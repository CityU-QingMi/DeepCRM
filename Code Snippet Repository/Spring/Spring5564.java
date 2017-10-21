	@Test
	public void throwsSetExceptionWrappedInExecutionExceptionFromCompletable() throws Exception {
		Throwable exception = new RuntimeException();
		assertTrue(settableListenableFuture.setException(exception));
		Future<String> completable = settableListenableFuture.completable();

		try {
			completable.get();
			fail("Expected ExecutionException");
		}
		catch (ExecutionException ex) {
			assertThat(ex.getCause(), equalTo(exception));
		}

		assertFalse(completable.isCancelled());
		assertTrue(completable.isDone());
	}
