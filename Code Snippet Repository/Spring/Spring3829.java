	@Test
	public void asyncMethodsThroughInterface() throws Exception {
		originalThreadName = Thread.currentThread().getName();
		GenericApplicationContext context = new GenericApplicationContext();
		context.registerBeanDefinition("asyncTest", new RootBeanDefinition(SimpleAsyncMethodBean.class));
		context.registerBeanDefinition("autoProxyCreator", new RootBeanDefinition(DefaultAdvisorAutoProxyCreator.class));
		context.registerBeanDefinition("asyncAdvisor", new RootBeanDefinition(AsyncAnnotationAdvisor.class));
		context.refresh();

		SimpleInterface asyncTest = context.getBean("asyncTest", SimpleInterface.class);
		asyncTest.doNothing(5);
		asyncTest.doSomething(10);
		Future<String> future = asyncTest.returnSomething(20);
		assertEquals("20", future.get());
	}
