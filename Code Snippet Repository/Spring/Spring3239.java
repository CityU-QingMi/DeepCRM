	@Test
	public void testLazyInitTrue() {
		GenericApplicationContext context = new GenericApplicationContext();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
		reader.loadBeanDefinitions(LOCATION_PREFIX + "defaultLazyInitTrueTests.xml");
		assertTrue("lazy-init should be true", context.getBeanDefinition(TEST_BEAN_NAME).isLazyInit());
		assertEquals("initCount should be 0", 0, DefaultsTestBean.INIT_COUNT);
		context.refresh();
		assertEquals("bean should not have been instantiated yet", 0, DefaultsTestBean.INIT_COUNT);
		context.getBean(TEST_BEAN_NAME);
		assertEquals("bean should have been instantiated", 1, DefaultsTestBean.INIT_COUNT);
	}
