	@Test
	public void addServerEndpointConfigBeanWithExplicitServerContainer() throws Exception {
		ServerEndpointRegistration endpointRegistration = new ServerEndpointRegistration("/dummy", new DummyEndpoint());
		this.webAppContext.getBeanFactory().registerSingleton("dummyEndpoint", endpointRegistration);
		this.servletContext.removeAttribute("javax.websocket.server.ServerContainer");

		this.exporter.setServerContainer(this.serverContainer);
		this.exporter.setApplicationContext(this.webAppContext);
		this.exporter.afterPropertiesSet();
		this.exporter.afterSingletonsInstantiated();

		verify(this.serverContainer).addEndpoint(endpointRegistration);
	}
