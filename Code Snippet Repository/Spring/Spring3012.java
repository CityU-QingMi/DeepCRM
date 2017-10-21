	@Test
	public void testNonLenientDependencyMatching() {
		DefaultListableBeanFactory xbf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(xbf).loadBeanDefinitions(CONSTRUCTOR_ARG_CONTEXT);
		AbstractBeanDefinition bd = (AbstractBeanDefinition) xbf.getBeanDefinition("lenientDependencyTestBean");
		bd.setLenientConstructorResolution(false);
		try {
			xbf.getBean("lenientDependencyTestBean");
			fail("Should have thrown BeanCreationException");
		}
		catch (BeanCreationException ex) {
			// expected
			ex.printStackTrace();
			assertTrue(ex.getMostSpecificCause().getMessage().contains("Ambiguous"));
		}
	}
