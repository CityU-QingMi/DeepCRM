	@Test(expected = ExecutionException.class)
	public void submitFailingRunnableWithoutErrorHandler() throws Exception {
		TestTask task = new TestTask(0);
		Future<?> future = scheduler.submit(task);
		try {
			future.get(1000, TimeUnit.MILLISECONDS);
		}
		catch (ExecutionException e) {
			assertTrue(future.isDone());
			throw e;
		}
	}
