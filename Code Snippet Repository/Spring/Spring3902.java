	@Test(expected = ExecutionException.class)
	public void scheduleOneTimeFailingTaskWithoutErrorHandler() throws Exception {
		TestTask task = new TestTask(0);
		Future<?> future = scheduler.schedule(task, new Date());
		try {
			future.get(1000, TimeUnit.MILLISECONDS);
		}
		catch (ExecutionException e) {
			assertTrue(future.isDone());
			throw e;
		}
	}
