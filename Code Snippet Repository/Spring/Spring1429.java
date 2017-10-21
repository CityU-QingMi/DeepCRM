	@Test
	public void testWhenTargetBeanNameIsWhitespacedString() throws Exception {
		try {
			ObjectFactoryCreatingFactoryBean factory = new ObjectFactoryCreatingFactoryBean();
			factory.setTargetBeanName("  \t");
			factory.afterPropertiesSet();
			fail("Must have thrown an IllegalArgumentException; 'targetBeanName' property set to (invalid) only-whitespace string.");
		}
		catch (IllegalArgumentException expected) {}
	}
