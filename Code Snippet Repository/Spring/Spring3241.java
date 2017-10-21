	@Test
	public void testDefaultAutowire() {
		GenericApplicationContext context = new GenericApplicationContext();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
		reader.loadBeanDefinitions(LOCATION_PREFIX + "defaultWithNoOverridesTests.xml");
		context.refresh();
		DefaultsTestBean bean = (DefaultsTestBean) context.getBean(TEST_BEAN_NAME);
		assertNull("no dependencies should have been autowired", bean.getConstructorDependency());
		assertNull("no dependencies should have been autowired", bean.getPropertyDependency1());
		assertNull("no dependencies should have been autowired", bean.getPropertyDependency2());
	}
