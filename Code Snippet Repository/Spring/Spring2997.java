	@Test
	public void testClassNotFoundWithDefaultBeanClassLoader() {
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(factory).loadBeanDefinitions(CLASS_NOT_FOUND_CONTEXT);
		// cool, no errors, so the rubbish class name in the bean def was not resolved
		try {
			// let's resolve the bean definition; must blow up
			factory.getBean("classNotFound");
			fail("Must have thrown a CannotLoadBeanClassException");
		}
		catch (CannotLoadBeanClassException ex) {
			assertTrue(ex.getResourceDescription().indexOf("classNotFound.xml") != -1);
			assertTrue(ex.getCause() instanceof ClassNotFoundException);
		}
	}
