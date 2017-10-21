	@Test
	public void testCanSpecifyFactoryMethodArgumentsOnFactoryMethodPrototype() {
		DefaultListableBeanFactory xbf = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(xbf);
		reader.loadBeanDefinitions(new ClassPathResource("factory-methods.xml", getClass()));
		TestBean tbArg = new TestBean();
		tbArg.setName("arg1");
		TestBean tbArg2 = new TestBean();
		tbArg2.setName("arg2");

		FactoryMethods fm1 = (FactoryMethods) xbf.getBean("testBeanOnlyPrototype", tbArg);
		assertEquals(0, fm1.getNum());
		assertEquals("default", fm1.getName());
		// This comes from the test bean
		assertEquals("arg1", fm1.getTestBean().getName());

		FactoryMethods fm2 = (FactoryMethods) xbf.getBean("testBeanOnlyPrototype", tbArg2);
		assertEquals("arg2", fm2.getTestBean().getName());
		assertEquals(fm1.getNum(), fm2.getNum());
		assertEquals(fm2.getStringValue(), "testBeanOnlyPrototypeDISetterString");
		assertEquals(fm2.getStringValue(), fm2.getStringValue());
		// The TestBean reference is resolved to a prototype in the factory
		assertSame(fm2.getTestBean(), fm2.getTestBean());
		assertNotSame(fm1, fm2);

		FactoryMethods fm3 = (FactoryMethods) xbf.getBean("testBeanOnlyPrototype", tbArg2, new Integer(1), "myName");
		assertEquals(1, fm3.getNum());
		assertEquals("myName", fm3.getName());
		assertEquals("arg2", fm3.getTestBean().getName());

		FactoryMethods fm4 = (FactoryMethods) xbf.getBean("testBeanOnlyPrototype", tbArg);
		assertEquals(0, fm4.getNum());
		assertEquals("default", fm4.getName());
		assertEquals("arg1", fm4.getTestBean().getName());
	}
