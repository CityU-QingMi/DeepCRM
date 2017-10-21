	@Test
	public void testDefaultInitAndDestroyMethodsDefined() {
		GenericApplicationContext context = new GenericApplicationContext();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
		reader.loadBeanDefinitions(LOCATION_PREFIX + "defaultInitAndDestroyMethodsTests.xml");
		context.refresh();
		DefaultsTestBean bean = (DefaultsTestBean) context.getBean(TEST_BEAN_NAME);
		assertTrue("bean should have been initialized", bean.isInitialized());
		context.close();
		assertTrue("bean should have been destroyed", bean.isDestroyed());
	}
