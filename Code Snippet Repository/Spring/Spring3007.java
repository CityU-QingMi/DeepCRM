	@Test
	public void testRejectsOverrideOfBogusMethodName() {
		DefaultListableBeanFactory xbf = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(xbf);
		try {
			reader.loadBeanDefinitions(INVALID_NO_SUCH_METHOD_CONTEXT);
			xbf.getBean("constructorOverrides");
			fail("Shouldn't allow override of bogus method");
		}
		catch (BeanDefinitionStoreException ex) {
			// Check that the bogus method name was included in the error message
			assertTrue("Bogus method name correctly reported", ex.getMessage().indexOf("bogusMethod") != -1);
		}
	}
