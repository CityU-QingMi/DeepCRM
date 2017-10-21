	@Test
	public void testPrototypeScriptedBean() throws Exception {
		GenericApplicationContext ctx = new GenericApplicationContext();
		ctx.registerBeanDefinition("messenger", BeanDefinitionBuilder.rootBeanDefinition(StubMessenger.class).getBeanDefinition());

		BeanDefinitionBuilder scriptedBeanBuilder = BeanDefinitionBuilder.rootBeanDefinition(GroovyScriptFactory.class);
		scriptedBeanBuilder.setScope(BeanDefinition.SCOPE_PROTOTYPE);
		scriptedBeanBuilder.addConstructorArgValue(DELEGATING_SCRIPT);
		scriptedBeanBuilder.addPropertyReference("messenger", "messenger");

		final String BEAN_WITH_DEPENDENCY_NAME = "needsMessenger";
		ctx.registerBeanDefinition(BEAN_WITH_DEPENDENCY_NAME, scriptedBeanBuilder.getBeanDefinition());
		ctx.registerBeanDefinition("scriptProcessor", createScriptFactoryPostProcessor(true));
		ctx.refresh();

		Messenger messenger1 = (Messenger) ctx.getBean(BEAN_WITH_DEPENDENCY_NAME);
		Messenger messenger2 = (Messenger) ctx.getBean(BEAN_WITH_DEPENDENCY_NAME);
		assertNotSame(messenger1, messenger2);
	}
