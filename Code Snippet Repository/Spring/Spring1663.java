	@Test
	public void innerBeanConfigured() throws Exception {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(beanFactory).loadBeanDefinitions(
				new ClassPathResource("simplePropertyNamespaceHandlerTests.xml", getClass()));
		TestBean sally = (TestBean) beanFactory.getBean("sally2");
		ITestBean rob = sally.getSpouse();
		assertEquals("Rob Harrop", rob.getName());
		assertEquals(24, rob.getAge());
		assertEquals(rob.getSpouse(), sally);
	}
