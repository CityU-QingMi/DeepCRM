	@Test
	public void testDefaultDependencyCheck() {
		GenericApplicationContext context = new GenericApplicationContext();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
		reader.loadBeanDefinitions(LOCATION_PREFIX + "defaultWithNoOverridesTests.xml");
		context.refresh();
		DefaultsTestBean bean = (DefaultsTestBean) context.getBean(TEST_BEAN_NAME);
		assertNull("constructor dependency should not have been autowired", bean.getConstructorDependency());
		assertNull("property dependencies should not have been autowired", bean.getPropertyDependency1());
		assertNull("property dependencies should not have been autowired", bean.getPropertyDependency2());
	}
