	@Test
	public void simpleBeanConfigured() throws Exception {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(beanFactory).loadBeanDefinitions(
				new ClassPathResource("simplePropertyNamespaceHandlerTests.xml", getClass()));
		ITestBean rob = (TestBean) beanFactory.getBean("rob");
		ITestBean sally = (TestBean) beanFactory.getBean("sally");
		assertEquals("Rob Harrop", rob.getName());
		assertEquals(24, rob.getAge());
		assertEquals(rob.getSpouse(), sally);
	}
