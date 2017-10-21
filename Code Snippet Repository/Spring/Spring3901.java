	@Test
	public void submitFailingCallableWithErrorHandler() throws Exception {
		TestCallable task = new TestCallable(0);
		TestErrorHandler errorHandler = new TestErrorHandler(1);
		scheduler.setErrorHandler(errorHandler);
		Future<String> future = scheduler.submit(task);
		Object result = future.get(1000, TimeUnit.MILLISECONDS);
		assertTrue(future.isDone());
		assertNull(result);
		assertNotNull(errorHandler.lastError);
	}
