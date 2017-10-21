	@Test
	public void testAutowireByName() {
		GenericApplicationContext context = new GenericApplicationContext();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
		reader.loadBeanDefinitions(LOCATION_PREFIX + "defaultAutowireByNameTests.xml");
		context.refresh();
		DefaultsTestBean bean = (DefaultsTestBean) context.getBean(TEST_BEAN_NAME);
		assertNull("constructor dependency should not have been autowired", bean.getConstructorDependency());
		assertNull("propertyDependency1 should not have been autowired", bean.getPropertyDependency1());
		assertNotNull("propertyDependency2 should have been autowired", bean.getPropertyDependency2());
		assertEquals("pd2", bean.getPropertyDependency2().getName());
	}
