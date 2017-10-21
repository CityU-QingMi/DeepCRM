	@Test
	public void testAutowiredAnnotatedConstructorSupported() {
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(factory).loadBeanDefinitions(
				new ClassPathResource("annotation-config.xml", MultipleConstructorConfig.class));
		GenericApplicationContext ctx = new GenericApplicationContext(factory);
		ctx.registerBeanDefinition("config1", new RootBeanDefinition(MultipleConstructorConfig.class));
		ctx.registerBeanDefinition("config2", new RootBeanDefinition(ColorConfig.class));
		ctx.refresh();
		assertSame(ctx.getBean(MultipleConstructorConfig.class).colour, ctx.getBean(Colour.class));
	}
