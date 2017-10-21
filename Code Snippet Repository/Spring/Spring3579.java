	@Test
	public void testPerformsLookup() throws Exception {
		LocalInterfaceWithBusinessMethods ejb = mock(LocalInterfaceWithBusinessMethods.class);

		String jndiName= "foobar";
		Context mockContext = mockContext(jndiName, ejb);

		configuredInterceptor(mockContext, jndiName);

		verify(mockContext).close();
	}
