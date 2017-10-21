	@Test
	public void testFactoryMethodsPrototypeOnTargetClass() {
		DefaultListableBeanFactory xbf = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(xbf);
		reader.loadBeanDefinitions(new ClassPathResource("factory-methods.xml", getClass()));
		FactoryMethods fm = (FactoryMethods) xbf.getBean("defaultPrototype");
		FactoryMethods fm2 = (FactoryMethods) xbf.getBean("defaultPrototype");
		assertEquals(0, fm.getNum());
		assertEquals("default", fm.getName());
		assertEquals("defaultInstance", fm.getTestBean().getName());
		assertEquals("setterString", fm.getStringValue());
		assertEquals(fm.getNum(), fm2.getNum());
		assertEquals(fm.getStringValue(), fm2.getStringValue());
		// The TestBean is created separately for each bean
		assertNotSame(fm.getTestBean(), fm2.getTestBean());
		assertNotSame(fm, fm2);

		fm = (FactoryMethods) xbf.getBean("testBeanOnlyPrototype");
		fm2 = (FactoryMethods) xbf.getBean("testBeanOnlyPrototype");
		assertEquals(0, fm.getNum());
		assertEquals("default", fm.getName());
		// This comes from the test bean
		assertEquals("Juergen", fm.getTestBean().getName());
		assertEquals(fm.getNum(), fm2.getNum());
		assertEquals(fm.getStringValue(), fm2.getStringValue());
		// The TestBean reference is resolved to a prototype in the factory
		assertSame(fm.getTestBean(), fm2.getTestBean());
		assertNotSame(fm, fm2);

		fm = (FactoryMethods) xbf.getBean("fullPrototype");
		fm2 = (FactoryMethods) xbf.getBean("fullPrototype");
		assertEquals(27, fm.getNum());
		assertEquals("gotcha", fm.getName());
		assertEquals("Juergen", fm.getTestBean().getName());
		assertEquals(fm.getNum(), fm2.getNum());
		assertEquals(fm.getStringValue(), fm2.getStringValue());
		// The TestBean reference is resolved to a prototype in the factory
		assertSame(fm.getTestBean(), fm2.getTestBean());
		assertNotSame(fm, fm2);
	}
