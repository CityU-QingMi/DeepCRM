	@Test
	public void asyncMethodsWithQualifier() throws Exception {
		originalThreadName = Thread.currentThread().getName();
		GenericApplicationContext context = new GenericApplicationContext();
		context.registerBeanDefinition("asyncTest", new RootBeanDefinition(AsyncMethodWithQualifierBean.class));
		context.registerBeanDefinition("autoProxyCreator", new RootBeanDefinition(DefaultAdvisorAutoProxyCreator.class));
		context.registerBeanDefinition("asyncAdvisor", new RootBeanDefinition(AsyncAnnotationAdvisor.class));
		context.registerBeanDefinition("e0", new RootBeanDefinition(ThreadPoolTaskExecutor.class));
		context.registerBeanDefinition("e1", new RootBeanDefinition(ThreadPoolTaskExecutor.class));
		context.registerBeanDefinition("e2", new RootBeanDefinition(ThreadPoolTaskExecutor.class));
		context.refresh();

		AsyncMethodWithQualifierBean asyncTest = context.getBean("asyncTest", AsyncMethodWithQualifierBean.class);
		asyncTest.doNothing(5);
		asyncTest.doSomething(10);
		Future<String> future = asyncTest.returnSomething(20);
		assertEquals("20", future.get());
		Future<String> future2 = asyncTest.returnSomething2(30);
		assertEquals("30", future2.get());
	}
