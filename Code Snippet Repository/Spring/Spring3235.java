	@Test
	public void testDefaultLazyInit() {
		GenericApplicationContext context = new GenericApplicationContext();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
		reader.loadBeanDefinitions(LOCATION_PREFIX + "defaultWithNoOverridesTests.xml");
		assertFalse("lazy-init should be false", context.getBeanDefinition(TEST_BEAN_NAME).isLazyInit());
		assertEquals("initCount should be 0", 0, DefaultsTestBean.INIT_COUNT);
		context.refresh();
		assertEquals("bean should have been instantiated", 1, DefaultsTestBean.INIT_COUNT);
	}
