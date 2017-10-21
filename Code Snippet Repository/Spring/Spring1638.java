	@Test
	public void testCanSpecifyFactoryMethodArgumentsOnSingleton() {
		DefaultListableBeanFactory xbf = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(xbf);
		reader.loadBeanDefinitions(new ClassPathResource("factory-methods.xml", getClass()));

		// First getBean call triggers actual creation of the singleton bean
		TestBean tb = new TestBean();
		FactoryMethods fm1 = (FactoryMethods) xbf.getBean("testBeanOnly", tb);
		assertSame(tb, fm1.getTestBean());
		FactoryMethods fm2 = (FactoryMethods) xbf.getBean("testBeanOnly", new TestBean());
		assertSame(fm1, fm2);
		assertSame(tb, fm2.getTestBean());
	}
