	@Test
	public void testReferencesAcrossAContainerHierarchy() throws Exception {
		GenericApplicationContext businessContext = new GenericApplicationContext();
		businessContext.registerBeanDefinition("messenger", BeanDefinitionBuilder.rootBeanDefinition(StubMessenger.class).getBeanDefinition());
		businessContext.refresh();

		BeanDefinitionBuilder scriptedBeanBuilder = BeanDefinitionBuilder.rootBeanDefinition(GroovyScriptFactory.class);
		scriptedBeanBuilder.addConstructorArgValue(DELEGATING_SCRIPT);
		scriptedBeanBuilder.addPropertyReference("messenger", "messenger");

		GenericApplicationContext presentationCtx = new GenericApplicationContext(businessContext);
		presentationCtx.registerBeanDefinition("needsMessenger", scriptedBeanBuilder.getBeanDefinition());
		presentationCtx.registerBeanDefinition("scriptProcessor", createScriptFactoryPostProcessor(true));
		presentationCtx.refresh();
	}
