	private void doTestRmiProxyFactoryBeanWithBusinessInterfaceAndException(
			Class<?> rmiExceptionClass, Class<?> springExceptionClass) throws Exception {

		CountingRmiProxyFactoryBean factory = new CountingRmiProxyFactoryBean();
		factory.setServiceInterface(IBusinessBean.class);
		factory.setServiceUrl("rmi://localhost:1090/test");
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
		assertEquals(1, factory.counter);
	}
