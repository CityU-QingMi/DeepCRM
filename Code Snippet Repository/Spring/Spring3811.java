	@Test
	public void rmiProxyFactoryBeanWithBusinessInterface() throws Exception {
		CountingRmiProxyFactoryBean factory = new CountingRmiProxyFactoryBean();
		factory.setServiceInterface(IBusinessBean.class);
		factory.setServiceUrl("rmi://localhost:1090/test");
		factory.afterPropertiesSet();
		assertTrue(factory.getObject() instanceof IBusinessBean);
		IBusinessBean proxy = (IBusinessBean) factory.getObject();
		assertFalse(proxy instanceof IRemoteBean);
		proxy.setName("myName");
		assertEquals(RemoteBean.name, "myName");
		assertEquals(1, factory.counter);
	}
