	@Test
	public void testDefaultNonExistingInitAndDestroyMethodsDefined() {
		GenericApplicationContext context = new GenericApplicationContext();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
		reader.loadBeanDefinitions(LOCATION_PREFIX + "defaultNonExistingInitAndDestroyMethodsTests.xml");
		context.refresh();
		DefaultsTestBean bean = (DefaultsTestBean) context.getBean(TEST_BEAN_NAME);
		assertFalse("bean should not have been initialized", bean.isInitialized());
		context.close();
		assertFalse("bean should not have been destroyed", bean.isDestroyed());
	}
