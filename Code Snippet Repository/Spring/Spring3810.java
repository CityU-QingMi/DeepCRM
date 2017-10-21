	private void doTestRmiProxyFactoryBeanWithExceptionAndRefresh(Class<?> exceptionClass) throws Exception {
		CountingRmiProxyFactoryBean factory = new CountingRmiProxyFactoryBean();
		factory.setServiceInterface(IRemoteBean.class);
		factory.setServiceUrl("rmi://localhost:1090/test");
		factory.setRefreshStubOnConnectFailure(true);
		factory.afterPropertiesSet();
		assertTrue(factory.getObject() instanceof IRemoteBean);
		IRemoteBean proxy = (IRemoteBean) factory.getObject();
		try {
			proxy.setName(exceptionClass.getName());
			fail("Should have thrown " + exceptionClass.getName());
		}
		catch (Exception ex) {
			if (exceptionClass.isInstance(ex)) {
				// expected
			}
			else {
				throw ex;
			}
		}
		assertEquals(2, factory.counter);
	}
