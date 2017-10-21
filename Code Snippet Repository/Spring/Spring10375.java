	@Test
	public void simpleHessianServiceExporter() throws IOException {
		final int port = SocketUtils.findAvailableTcpPort();

		TestBean tb = new TestBean("tb");
		SimpleHessianServiceExporter exporter = new SimpleHessianServiceExporter();
		exporter.setService(tb);
		exporter.setServiceInterface(ITestBean.class);
		exporter.setDebug(true);
		exporter.prepare();

		HttpServer server = HttpServer.create(new InetSocketAddress(port), -1);
		server.createContext("/hessian", exporter);
		server.start();
		try {
			HessianClientInterceptor client = new HessianClientInterceptor();
			client.setServiceUrl("http://localhost:" + port + "/hessian");
			client.setServiceInterface(ITestBean.class);
			//client.setHessian2(true);
			client.prepare();
			ITestBean proxy = ProxyFactory.getProxy(ITestBean.class, client);
			assertEquals("tb", proxy.getName());
			proxy.setName("test");
			assertEquals("test", proxy.getName());
		}
		finally {
			server.stop(Integer.MAX_VALUE);
		}
	}
