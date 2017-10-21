	@Test
	public void submitFailingRunnableWithErrorHandler() throws Exception {
		TestTask task = new TestTask(0);
		TestErrorHandler errorHandler = new TestErrorHandler(1);
		scheduler.setErrorHandler(errorHandler);
		Future<?> future = scheduler.submit(task);
		Object result = future.get(1000, TimeUnit.MILLISECONDS);
		assertTrue(future.isDone());
		assertNull(result);
		assertNotNull(errorHandler.lastError);
	}
