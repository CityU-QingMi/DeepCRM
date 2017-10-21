	@Test
	public void testFactoryBeansWithAutowiring() throws Exception {
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(factory).loadBeanDefinitions(WITH_AUTOWIRING_CONTEXT);

		BeanFactoryPostProcessor ppc = (BeanFactoryPostProcessor) factory.getBean("propertyPlaceholderConfigurer");
		ppc.postProcessBeanFactory(factory);

		assertNull(factory.getType("betaFactory"));

		Alpha alpha = (Alpha) factory.getBean("alpha");
		Beta beta = (Beta) factory.getBean("beta");
		Gamma gamma = (Gamma) factory.getBean("gamma");
		Gamma gamma2 = (Gamma) factory.getBean("gammaFactory");

		assertSame(beta, alpha.getBeta());
		assertSame(gamma, beta.getGamma());
		assertSame(gamma2, beta.getGamma());
		assertEquals("yourName", beta.getName());
	}
