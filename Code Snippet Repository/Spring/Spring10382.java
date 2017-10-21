	@Test
	public void httpInvokerProxyFactoryBeanAndServiceExporterWithIOException() throws Exception {
		TestBean target = new TestBean("myname", 99);

		final HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
		exporter.setServiceInterface(ITestBean.class);
		exporter.setService(target);
		exporter.afterPropertiesSet();

		HttpInvokerProxyFactoryBean pfb = new HttpInvokerProxyFactoryBean();
		pfb.setServiceInterface(ITestBean.class);
		pfb.setServiceUrl("http://myurl");

		pfb.setHttpInvokerRequestExecutor(new HttpInvokerRequestExecutor() {
			@Override
			public RemoteInvocationResult executeRequest(
					HttpInvokerClientConfiguration config, RemoteInvocation invocation) throws IOException {
				throw new IOException("argh");
			}
		});

		pfb.afterPropertiesSet();
		ITestBean proxy = (ITestBean) pfb.getObject();
		try {
			proxy.setAge(50);
			fail("Should have thrown RemoteAccessException");
		}
		catch (RemoteAccessException ex) {
			// expected
			assertTrue(ex.getCause() instanceof IOException);
		}
	}
