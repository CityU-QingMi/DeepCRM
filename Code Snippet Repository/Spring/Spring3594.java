	private void doTestInvokesMethodOnEjbInstance(boolean lookupHomeOnStartup, boolean cacheHome) throws Exception {
		Object retVal = new Object();
		final RemoteInterface ejb = mock(RemoteInterface.class);
		given(ejb.targetMethod()).willReturn(retVal);

		int lookupCount = 1;
		if (!cacheHome) {
			lookupCount++;
			if (lookupHomeOnStartup) {
				lookupCount++;
			}
		}

		final String jndiName= "foobar";
		Context mockContext = mockContext(jndiName, ejb);

		SimpleRemoteSlsbInvokerInterceptor si = configuredInterceptor(mockContext, jndiName);
		si.setLookupHomeOnStartup(lookupHomeOnStartup);
		si.setCacheHome(cacheHome);

		RemoteInterface target = (RemoteInterface) configuredProxy(si, RemoteInterface.class);
		assertTrue(target.targetMethod() == retVal);
		assertTrue(target.targetMethod() == retVal);

		verify(mockContext, times(lookupCount)).close();
		verify(ejb, times(2)).remove();
	}
