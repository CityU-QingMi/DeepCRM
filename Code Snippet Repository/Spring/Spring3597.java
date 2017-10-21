	@Test
	public void testInvokesMethodOnEjbInstanceWithBusinessInterface() throws Exception {
		Object retVal = new Object();
		final RemoteInterface ejb = mock(RemoteInterface.class);
		given(ejb.targetMethod()).willReturn(retVal);

		final String jndiName= "foobar";
		Context mockContext = mockContext(jndiName, ejb);

		SimpleRemoteSlsbInvokerInterceptor si = configuredInterceptor(mockContext, jndiName);

		BusinessInterface target = (BusinessInterface) configuredProxy(si, BusinessInterface.class);
		assertTrue(target.targetMethod() == retVal);

		verify(mockContext).close();
		verify(ejb).remove();
	}
