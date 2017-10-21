	private void doTestException(Exception expected) throws Exception {
		final RemoteInterface ejb = mock(RemoteInterface.class);
		given(ejb.targetMethod()).willThrow(expected);

		final String jndiName= "foobar";
		Context mockContext = mockContext(jndiName, ejb);

		SimpleRemoteSlsbInvokerInterceptor si = configuredInterceptor(mockContext, jndiName);

		RemoteInterface target = (RemoteInterface) configuredProxy(si, RemoteInterface.class);
		try {
			target.targetMethod();
			fail("Should have thrown remote exception");
		}
		catch (Exception thrown) {
			assertTrue(thrown == expected);
		}

		verify(mockContext).close();
		verify(ejb).remove();
	}
