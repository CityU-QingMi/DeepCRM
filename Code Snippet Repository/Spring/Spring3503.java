	@Test
	public void customLifecycleProcessorInstance() {
		BeanDefinition beanDefinition = new RootBeanDefinition(DefaultLifecycleProcessor.class);
		beanDefinition.getPropertyValues().addPropertyValue("timeoutPerShutdownPhase", 1000);
		StaticApplicationContext context = new StaticApplicationContext();
		context.registerBeanDefinition("lifecycleProcessor", beanDefinition);
		context.refresh();
		LifecycleProcessor bean = context.getBean("lifecycleProcessor", LifecycleProcessor.class);
		Object contextLifecycleProcessor = new DirectFieldAccessor(context).getPropertyValue("lifecycleProcessor");
		assertNotNull(contextLifecycleProcessor);
		assertSame(bean, contextLifecycleProcessor);
		assertEquals(1000L, new DirectFieldAccessor(contextLifecycleProcessor).getPropertyValue(
				"timeoutPerShutdownPhase"));
	}
