	@Test
	public void testAutowireConstructor() {
		GenericApplicationContext context = new GenericApplicationContext();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
		reader.loadBeanDefinitions(LOCATION_PREFIX + "defaultAutowireConstructorTests.xml");
		context.refresh();
		DefaultsTestBean bean = (DefaultsTestBean) context.getBean(TEST_BEAN_NAME);
		assertNotNull("constructor dependency should have been autowired", bean.getConstructorDependency());
		assertEquals("cd", bean.getConstructorDependency().getName());
		assertNull("property dependencies should not have been autowired", bean.getPropertyDependency1());
		assertNull("property dependencies should not have been autowired", bean.getPropertyDependency2());
	}
