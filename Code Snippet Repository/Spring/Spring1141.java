	@Test
	public void testPrototypeStringCreatedRepeatedly() {
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		RootBeanDefinition stringDef = new RootBeanDefinition(String.class);
		stringDef.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		stringDef.getConstructorArgumentValues().addGenericArgumentValue(new TypedStringValue("value"));
		lbf.registerBeanDefinition("string", stringDef);
		String val1 = lbf.getBean("string", String.class);
		String val2 = lbf.getBean("string", String.class);
		assertEquals("value", val1);
		assertEquals("value", val2);
		assertNotSame(val1, val2);
	}
