	@Test
	public void testWithDuplicateNameInAlias() throws Exception {
		DefaultListableBeanFactory xbf = new DefaultListableBeanFactory();
		try {
			new XmlBeanDefinitionReader(xbf).loadBeanDefinitions(TEST_WITH_DUP_NAME_IN_ALIAS_CONTEXT);
			fail("Duplicate name not detected");
		}
		catch (BeansException e) {
			assertTrue(e.getMessage().contains("Bean name 'foo'"));
		}
	}
