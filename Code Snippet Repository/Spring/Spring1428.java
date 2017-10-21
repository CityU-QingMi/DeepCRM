	@Test
	public void testWhenTargetBeanNameIsEmptyString() throws Exception {
		try {
			ObjectFactoryCreatingFactoryBean factory = new ObjectFactoryCreatingFactoryBean();
			factory.setTargetBeanName("");
			factory.afterPropertiesSet();
			fail("Must have thrown an IllegalArgumentException; 'targetBeanName' property set to (invalid) empty string.");
		}
		catch (IllegalArgumentException expected) {}
	}
