	@Test
	public void testFactoryMethodsWithNullValue() {
		DefaultListableBeanFactory xbf = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(xbf);
		reader.loadBeanDefinitions(new ClassPathResource("factory-methods.xml", getClass()));

		FactoryMethods fm = (FactoryMethods) xbf.getBean("fullWithNull");
		assertEquals(27, fm.getNum());
		assertEquals(null, fm.getName());
		assertEquals("Juergen", fm.getTestBean().getName());

		fm = (FactoryMethods) xbf.getBean("fullWithGenericNull");
		assertEquals(27, fm.getNum());
		assertEquals(null, fm.getName());
		assertEquals("Juergen", fm.getTestBean().getName());

		fm = (FactoryMethods) xbf.getBean("fullWithNamedNull");
		assertEquals(27, fm.getNum());
		assertEquals(null, fm.getName());
		assertEquals("Juergen", fm.getTestBean().getName());
	}
