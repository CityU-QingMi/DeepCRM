	@Test
	public void testInstanceFactoryMethodWithoutArgs() {
		DefaultListableBeanFactory xbf = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(xbf);
		reader.loadBeanDefinitions(new ClassPathResource("factory-methods.xml", getClass()));

		InstanceFactory.count = 0;
		xbf.preInstantiateSingletons();
		assertEquals(1, InstanceFactory.count);
		FactoryMethods fm = (FactoryMethods) xbf.getBean("instanceFactoryMethodWithoutArgs");
		assertEquals("instanceFactory", fm.getTestBean().getName());
		assertEquals(1, InstanceFactory.count);
		FactoryMethods fm2 = (FactoryMethods) xbf.getBean("instanceFactoryMethodWithoutArgs");
		assertEquals("instanceFactory", fm2.getTestBean().getName());
		assertSame(fm2, fm);
		assertEquals(1, InstanceFactory.count);
	}
