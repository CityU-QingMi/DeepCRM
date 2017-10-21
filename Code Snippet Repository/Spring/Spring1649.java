	@Test
	public void testFactoryMethodNoMatchingStaticMethod() {
		DefaultListableBeanFactory xbf = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(xbf);
		reader.loadBeanDefinitions(new ClassPathResource("factory-methods.xml", getClass()));
		try {
			xbf.getBean("noMatchPrototype");
			fail("No static method matched");
		}
		catch (BeanCreationException ex) {
			// Ok
		}
	}
