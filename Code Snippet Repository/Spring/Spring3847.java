	@Test
	public void asyncClassWithInterfaceAndPostProcessor() throws Exception {
		originalThreadName = Thread.currentThread().getName();
		GenericApplicationContext context = new GenericApplicationContext();
		context.registerBeanDefinition("asyncTest", new RootBeanDefinition(AsyncClassBeanWithInterface.class));
		context.registerBeanDefinition("asyncProcessor", new RootBeanDefinition(AsyncAnnotationBeanPostProcessor.class));
		context.refresh();

		RegularInterface asyncTest = context.getBean("asyncTest", RegularInterface.class);
		asyncTest.doSomething(10);
		Future<String> future = asyncTest.returnSomething(20);
		assertEquals("20", future.get());
	}
