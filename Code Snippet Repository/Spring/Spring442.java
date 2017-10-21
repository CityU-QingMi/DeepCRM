	@Test
	public void asyncMethodReturningFutureGetsRoutedAsynchronouslyAndReturnsAFuture() throws InterruptedException, ExecutionException {
		ClassWithoutAsyncAnnotation obj = new ClassWithoutAsyncAnnotation();
		Future<Integer> future = obj.incrementReturningAFuture();
		// No need to executor.waitForCompletion() as future.get() will have the same effect
		assertEquals(5, future.get().intValue());
		assertEquals(1, obj.counter);
		assertEquals(1, executor.submitStartCounter);
		assertEquals(1, executor.submitCompleteCounter);
	}
