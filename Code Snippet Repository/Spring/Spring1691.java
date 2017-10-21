	@Test
	public void testResolveInvalidHandler() throws Exception {
		String mappingPath = "org/springframework/beans/factory/xml/support/invalid.properties";
		try {
			new DefaultNamespaceHandlerResolver(getClass().getClassLoader(), mappingPath);
			fail("Should not be able to map a class that doesn't implement NamespaceHandler");
		}
		catch (Throwable expected) {
		}
	}
