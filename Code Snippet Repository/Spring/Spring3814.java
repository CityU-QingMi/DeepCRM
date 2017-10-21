	private void doTestRmiProxyFactoryBeanWithBusinessInterfaceAndExceptionAndRefresh(
			Class<?> rmiExceptionClass, Class<?> springExceptionClass) throws Exception {

		CountingRmiProxyFactoryBean factory = new CountingRmiProxyFactoryBean();
		factory.setServiceInterface(IBusinessBean.class);
		factory.setServiceUrl("rmi://localhost:1090/test");
		factory.setRefreshStubOnConnectFailure(true);
		factory.afterPropertiesSet();
		assertTrue(factory.getObject() instanceof IBusinessBean);
		IBusinessBean proxy = (IBusinessBean) factory.getObject();
		assertFalse(proxy instanceof IRemoteBean);
		try {
			proxy.setName(rmiExceptionClass.getName());
			fail("Should have thrown " + rmiExceptionClass.getName());
		}
		catch (Exception ex) {
			if (springExceptionClass.isInstance(ex)) {
				// expected
			}
			else {
				throw ex;
			}
		}
		if (RemoteConnectFailureException.class.isAssignableFrom(springExceptionClass)) {
			assertEquals(2, factory.counter);
		}
		else {
			assertEquals(1, factory.counter);
		}
	}
