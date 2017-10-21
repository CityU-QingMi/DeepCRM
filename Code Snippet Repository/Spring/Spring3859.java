	@Test
	public void withAsyncBeanWithExecutorQualifiedByName() throws ExecutionException, InterruptedException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(AsyncWithExecutorQualifiedByNameConfig.class);
		ctx.refresh();

		AsyncBeanWithExecutorQualifiedByName asyncBean = ctx.getBean(AsyncBeanWithExecutorQualifiedByName.class);
		Future<Thread> workerThread0 = asyncBean.work0();
		assertThat(workerThread0.get().getName(), not(anyOf(startsWith("e1-"), startsWith("otherExecutor-"))));
		Future<Thread> workerThread = asyncBean.work();
		assertThat(workerThread.get().getName(), startsWith("e1-"));
		Future<Thread> workerThread2 = asyncBean.work2();
		assertThat(workerThread2.get().getName(), startsWith("otherExecutor-"));
		Future<Thread> workerThread3 = asyncBean.work3();
		assertThat(workerThread3.get().getName(), startsWith("otherExecutor-"));
	}
