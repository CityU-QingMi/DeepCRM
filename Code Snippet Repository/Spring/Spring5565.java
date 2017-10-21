	@Test
	public void throwsSetErrorWrappedInExecutionException() throws Exception {
		Throwable exception = new OutOfMemoryError();
		assertTrue(settableListenableFuture.setException(exception));

		try {
			settableListenableFuture.get();
			fail("Expected ExecutionException");
		}
		catch (ExecutionException ex) {
			assertThat(ex.getCause(), equalTo(exception));
		}

		assertFalse(settableListenableFuture.isCancelled());
		assertTrue(settableListenableFuture.isDone());
	}
