	@Test
	public void testInvalidClassNameScopeMetadataResolver() {
		try {
			new ClassPathXmlApplicationContext(
					"org/springframework/context/annotation/invalidClassNameScopeResolverTests.xml");
			fail("should have failed: no such class");
		}
		catch (BeansException ex) {
			// expected
		}
	}
