	@Test
	public void rmiProxyFactoryBeanWithWrongBusinessInterface() throws Exception {
		CountingRmiProxyFactoryBean factory = new CountingRmiProxyFactoryBean();
		factory.setServiceInterface(IWrongBusinessBean.class);
		factory.setServiceUrl("rmi://localhost:1090/test");
		factory.afterPropertiesSet();
		assertTrue(factory.getObject() instanceof IWrongBusinessBean);
		IWrongBusinessBean proxy = (IWrongBusinessBean) factory.getObject();
		assertFalse(proxy instanceof IRemoteBean);
		try {
			proxy.setOtherName("name");
			fail("Should have thrown RemoteProxyFailureException");
		}
		catch (RemoteProxyFailureException ex) {
			assertTrue(ex.getCause() instanceof NoSuchMethodException);
			assertTrue(ex.getMessage().contains("setOtherName"));
			assertTrue(ex.getMessage().contains("IWrongBusinessBean"));
		}
		assertEquals(1, factory.counter);
	}
