	@Test
	public void testFactoryMethodsOnExternalClass() {
		DefaultListableBeanFactory xbf = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(xbf);
		reader.loadBeanDefinitions(new ClassPathResource("factory-methods.xml", getClass()));

		assertEquals(TestBean.class, xbf.getType("externalFactoryMethodWithoutArgs"));
		assertEquals(TestBean.class, xbf.getType("externalFactoryMethodWithArgs"));
		String[] names = xbf.getBeanNamesForType(TestBean.class);
		assertTrue(Arrays.asList(names).contains("externalFactoryMethodWithoutArgs"));
		assertTrue(Arrays.asList(names).contains("externalFactoryMethodWithArgs"));

		TestBean tb = (TestBean) xbf.getBean("externalFactoryMethodWithoutArgs");
		assertEquals(2, tb.getAge());
		assertEquals("Tristan", tb.getName());
		tb = (TestBean) xbf.getBean("externalFactoryMethodWithArgs");
		assertEquals(33, tb.getAge());
		assertEquals("Rod", tb.getName());

		assertEquals(TestBean.class, xbf.getType("externalFactoryMethodWithoutArgs"));
		assertEquals(TestBean.class, xbf.getType("externalFactoryMethodWithArgs"));
		names = xbf.getBeanNamesForType(TestBean.class);
		assertTrue(Arrays.asList(names).contains("externalFactoryMethodWithoutArgs"));
		assertTrue(Arrays.asList(names).contains("externalFactoryMethodWithArgs"));
	}
