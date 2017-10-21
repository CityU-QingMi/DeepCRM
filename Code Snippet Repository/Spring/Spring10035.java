	@Test
	public void normal() {
		Proxy.Type type = Proxy.Type.HTTP;
		factoryBean.setType(type);
		String hostname = "example.com";
		factoryBean.setHostname(hostname);
		int port = 8080;
		factoryBean.setPort(port);
		factoryBean.afterPropertiesSet();

		Proxy result = factoryBean.getObject();

		assertEquals(type, result.type());
		InetSocketAddress address = (InetSocketAddress) result.address();
		assertEquals(hostname, address.getHostName());
		assertEquals(port, address.getPort());
	}
