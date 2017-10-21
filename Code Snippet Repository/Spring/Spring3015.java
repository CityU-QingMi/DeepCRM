	@Test
	public void testWithDuplicateName() throws Exception {
		DefaultListableBeanFactory xbf = new DefaultListableBeanFactory();
		try {
			new XmlBeanDefinitionReader(xbf).loadBeanDefinitions(TEST_WITH_DUP_NAMES_CONTEXT);
			fail("Duplicate name not detected");
		}
		catch (BeansException ex) {
			assertTrue(ex.getMessage().contains("Bean name 'foo'"));
		}
	}
