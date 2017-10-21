	@Test
	public void defaultExecutor() throws Exception {
		ApplicationContext context = new AnnotationConfigApplicationContext(ExecutorConfig.class);
		ExecutorService executor = context.getBean("executor", ExecutorService.class);

		FutureTask<String> task = new FutureTask<>(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "foo";
			}
		});
		executor.execute(task);
		assertEquals("foo", task.get());
	}
