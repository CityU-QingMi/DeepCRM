	@Test
	public void testInvokesMethodOnEjbInstanceWithRemoteException() throws Exception {
		final RemoteInterface ejb = mock(RemoteInterface.class);
		given(ejb.targetMethod()).willThrow(new RemoteException());
		ejb.remove();

		final String jndiName= "foobar";
		Context mockContext = mockContext(jndiName, ejb);

		SimpleRemoteSlsbInvokerInterceptor si = configuredInterceptor(mockContext, jndiName);

		RemoteInterface target = (RemoteInterface) configuredProxy(si, RemoteInterface.class);
		try {
			target.targetMethod();
			fail("Should have thrown RemoteException");
		}
		catch (RemoteException ex) {
			// expected
		}

		verify(mockContext).close();
		verify(ejb, times(2)).remove();
	}
