	@Test
	public void defaultExecutor() throws Exception {
		ThreadPoolTaskExecutor executor = this.context.getBean("default", ThreadPoolTaskExecutor.class);
		assertEquals(1, getCorePoolSize(executor));
		assertEquals(Integer.MAX_VALUE, getMaxPoolSize(executor));
		assertEquals(Integer.MAX_VALUE, getQueueCapacity(executor));
		assertEquals(60, getKeepAliveSeconds(executor));
		assertEquals(false, getAllowCoreThreadTimeOut(executor));

		FutureTask<String> task = new FutureTask<>(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "foo";
			}
		});
		executor.execute(task);
		assertEquals("foo", task.get());
	}
