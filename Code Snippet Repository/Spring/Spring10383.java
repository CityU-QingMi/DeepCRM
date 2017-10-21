	@Test
	public void httpInvokerWithSpecialLocalMethods() throws Exception {
		String serviceUrl = "http://myurl";
		HttpInvokerProxyFactoryBean pfb = new HttpInvokerProxyFactoryBean();
		pfb.setServiceInterface(ITestBean.class);
		pfb.setServiceUrl(serviceUrl);

		pfb.setHttpInvokerRequestExecutor(new HttpInvokerRequestExecutor() {
			@Override
			public RemoteInvocationResult executeRequest(
					HttpInvokerClientConfiguration config, RemoteInvocation invocation) throws IOException {
				throw new IOException("argh");
			}
		});

		pfb.afterPropertiesSet();
		ITestBean proxy = (ITestBean) pfb.getObject();

		// shouldn't go through to remote service
		assertTrue(proxy.toString().indexOf("HTTP invoker") != -1);
		assertTrue(proxy.toString().indexOf(serviceUrl) != -1);
		assertEquals(proxy.hashCode(), proxy.hashCode());
		assertTrue(proxy.equals(proxy));

		// should go through
		try {
			proxy.setAge(50);
			fail("Should have thrown RemoteAccessException");
		}
		catch (RemoteAccessException ex) {
			// expected
			assertTrue(ex.getCause() instanceof IOException);
		}
	}
