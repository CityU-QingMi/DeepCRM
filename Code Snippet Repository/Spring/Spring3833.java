	@Test
	public void asyncMethodsInInterfaceWithPostProcessor() throws Exception {
		originalThreadName = Thread.currentThread().getName();
		GenericApplicationContext context = new GenericApplicationContext();
		context.registerBeanDefinition("asyncTest", new RootBeanDefinition(AsyncMethodsInterfaceBean.class));
		context.registerBeanDefinition("asyncProcessor", new RootBeanDefinition(AsyncAnnotationBeanPostProcessor.class));
		context.refresh();

		AsyncMethodsInterface asyncTest = context.getBean("asyncTest", AsyncMethodsInterface.class);
		asyncTest.doNothing(5);
		asyncTest.doSomething(10);
		Future<String> future = asyncTest.returnSomething(20);
		assertEquals("20", future.get());
	}
