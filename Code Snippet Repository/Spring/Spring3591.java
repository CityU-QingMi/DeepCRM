	@Test
	public void testPerformsLookup() throws Exception {
		RemoteInterface ejb = mock(RemoteInterface.class);

		String jndiName= "foobar";
		Context mockContext = mockContext(jndiName, ejb);

		SimpleRemoteSlsbInvokerInterceptor si = configuredInterceptor(mockContext, jndiName);
		configuredProxy(si, RemoteInterface.class);

		verify(mockContext).close();
	}
