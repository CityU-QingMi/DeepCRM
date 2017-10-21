	@Test
	public void endpointSingleton() throws Exception {

		EchoEndpoint endpoint = new EchoEndpoint(new EchoService());
		ServerEndpointRegistration registration = new ServerEndpointRegistration("/path", endpoint);

		EchoEndpoint actual = registration.getConfigurator().getEndpointInstance(EchoEndpoint.class);

		assertSame(endpoint, actual);
	}
