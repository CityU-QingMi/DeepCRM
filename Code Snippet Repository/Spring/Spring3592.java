	@Test
	public void testPerformsLookupWithAccessContext() throws Exception {
		RemoteInterface ejb = mock(RemoteInterface.class);

		String jndiName= "foobar";
		Context mockContext = mockContext(jndiName, ejb);

		SimpleRemoteSlsbInvokerInterceptor si = configuredInterceptor(mockContext, jndiName);
		si.setExposeAccessContext(true);
		RemoteInterface target = (RemoteInterface) configuredProxy(si, RemoteInterface.class);
		assertNull(target.targetMethod());

		verify(mockContext, times(2)).close();
		verify(ejb).targetMethod();

	}
