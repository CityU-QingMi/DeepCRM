	@Test
	public void testObjectFactoryConstructorWithTypeVariable() {
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(factory).loadBeanDefinitions(
				new ClassPathResource("annotation-config.xml", ObjectFactoryConstructorConfig.class));
		GenericApplicationContext ctx = new GenericApplicationContext(factory);
		ctx.registerBeanDefinition("config1", new RootBeanDefinition(ObjectFactoryConstructorConfig.class));
		ctx.registerBeanDefinition("config2", new RootBeanDefinition(ColorConfig.class));
		ctx.refresh();
		assertSame(ctx.getBean(ObjectFactoryConstructorConfig.class).colour, ctx.getBean(Colour.class));
	}
