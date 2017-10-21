	@Test
	public void qualifiedAsyncMethodsAreRoutedToCorrectExecutor() throws InterruptedException, ExecutionException {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		beanFactory.registerBeanDefinition("e1", new RootBeanDefinition(ThreadPoolTaskExecutor.class));
		AnnotationAsyncExecutionAspect.aspectOf().setBeanFactory(beanFactory);

		ClassWithQualifiedAsyncMethods obj = new ClassWithQualifiedAsyncMethods();

		Future<Thread> defaultThread = obj.defaultWork();
		assertThat(defaultThread.get(), not(Thread.currentThread()));
		assertThat(defaultThread.get().getName(), not(startsWith("e1-")));

		ListenableFuture<Thread> e1Thread = obj.e1Work();
		assertThat(e1Thread.get().getName(), startsWith("e1-"));

		CompletableFuture<Thread> e1OtherThread = obj.e1OtherWork();
		assertThat(e1OtherThread.get().getName(), startsWith("e1-"));
	}
