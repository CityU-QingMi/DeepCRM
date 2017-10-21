	@Test
	public void rmiInvokerWithSpecialLocalMethods() throws Exception {
		String serviceUrl = "rmi://localhost:1090/test";
		RmiProxyFactoryBean factory = new RmiProxyFactoryBean() {
			@Override
			protected Remote lookupStub() {
				return new RmiInvocationHandler() {
					@Override
					public String getTargetInterfaceName() {
						return null;
					}
					@Override
					public Object invoke(RemoteInvocation invocation) throws RemoteException {
						throw new RemoteException();
					}
				};
			}
		};
		factory.setServiceInterface(IBusinessBean.class);
		factory.setServiceUrl(serviceUrl);
		factory.afterPropertiesSet();
		IBusinessBean proxy = (IBusinessBean) factory.getObject();

		// shouldn't go through to remote service
		assertTrue(proxy.toString().contains("RMI invoker"));
		assertTrue(proxy.toString().contains(serviceUrl));
		assertEquals(proxy.hashCode(), proxy.hashCode());
		assertTrue(proxy.equals(proxy));

		// should go through
		try {
			proxy.setName("test");
			fail("Should have thrown RemoteAccessException");
		}
		catch (RemoteAccessException ex) {
			// expected
		}
	}
