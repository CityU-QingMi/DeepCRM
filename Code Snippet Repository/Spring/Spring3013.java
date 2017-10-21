	@Test
	public void testNonLenientDependencyMatchingFactoryMethod() {
		DefaultListableBeanFactory xbf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(xbf).loadBeanDefinitions(CONSTRUCTOR_ARG_CONTEXT);
		AbstractBeanDefinition bd = (AbstractBeanDefinition) xbf.getBeanDefinition("lenientDependencyTestBeanFactoryMethod");
		bd.setLenientConstructorResolution(false);
		try {
			xbf.getBean("lenientDependencyTestBeanFactoryMethod");
			fail("Should have thrown BeanCreationException");
		}
		catch (BeanCreationException ex) {
			// expected
			ex.printStackTrace();
			assertTrue(ex.getMostSpecificCause().getMessage().contains("Ambiguous"));
		}
	}
