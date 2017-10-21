	@Test
	public void testMissingPointcutConfig() {
		try {
			DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
			new XmlBeanDefinitionReader(bf).loadBeanDefinitions(
					qualifiedResource(getClass(), "pointcutMissing.xml"));
			fail("parsing should have caused a BeanDefinitionStoreException");
		}
		catch (BeanDefinitionStoreException ex) {
			assertTrue(ex.contains(BeanDefinitionParsingException.class));
		}
	}
