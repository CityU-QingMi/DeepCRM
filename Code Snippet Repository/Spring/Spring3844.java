	@Test
	public void asyncClass() throws Exception {
		originalThreadName = Thread.currentThread().getName();
		GenericApplicationContext context = new GenericApplicationContext();
		context.registerBeanDefinition("asyncTest", new RootBeanDefinition(AsyncClassBean.class));
		context.registerBeanDefinition("autoProxyCreator", new RootBeanDefinition(DefaultAdvisorAutoProxyCreator.class));
		context.registerBeanDefinition("asyncAdvisor", new RootBeanDefinition(AsyncAnnotationAdvisor.class));
		context.refresh();

		AsyncClassBean asyncTest = context.getBean("asyncTest", AsyncClassBean.class);
		asyncTest.doSomething(10);
		Future<String> future = asyncTest.returnSomething(20);
		assertEquals("20", future.get());
		ListenableFuture<String> listenableFuture = asyncTest.returnSomethingListenable(20);
		assertEquals("20", listenableFuture.get());
		CompletableFuture<String> completableFuture = asyncTest.returnSomethingCompletable(20);
		assertEquals("20", completableFuture.get());

		try {
			asyncTest.returnSomething(0).get();
			fail("Should have thrown ExecutionException");
		}
		catch (ExecutionException ex) {
			assertTrue(ex.getCause() instanceof IllegalArgumentException);
		}

		try {
			asyncTest.returnSomethingListenable(0).get();
			fail("Should have thrown ExecutionException");
		}
		catch (ExecutionException ex) {
			assertTrue(ex.getCause() instanceof IllegalArgumentException);
		}

		try {
			asyncTest.returnSomethingCompletable(0).get();
			fail("Should have thrown ExecutionException");
		}
		catch (ExecutionException ex) {
			assertTrue(ex.getCause() instanceof IllegalArgumentException);
		}
	}
