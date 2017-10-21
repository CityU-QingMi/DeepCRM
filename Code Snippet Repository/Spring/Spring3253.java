	@Test
	public void testInvalidConstructorBeanNameGenerator() {
		try {
			new ClassPathXmlApplicationContext(
					"org/springframework/context/annotation/invalidConstructorNameGeneratorTests.xml");
			fail("should have failed: no-arg constructor is required");
		}
		catch (BeansException ex) {
			// expected
		}
	}
