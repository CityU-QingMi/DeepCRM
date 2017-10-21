	@Test
	public void testDuplicatePointcutConfig() {
		try {
			DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
			new XmlBeanDefinitionReader(bf).loadBeanDefinitions(
					qualifiedResource(getClass(), "pointcutDuplication.xml"));
			fail("parsing should have caused a BeanDefinitionStoreException");
		}
		catch (BeanDefinitionStoreException ex) {
			assertTrue(ex.contains(BeanDefinitionParsingException.class));
		}
	}
