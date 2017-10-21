	@Test
	public void endpointPerConnection() throws Exception {

		@SuppressWarnings("resource")
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

		ServerEndpointRegistration registration = new ServerEndpointRegistration("/path", EchoEndpoint.class);
		registration.setBeanFactory(context.getBeanFactory());

		EchoEndpoint endpoint = registration.getConfigurator().getEndpointInstance(EchoEndpoint.class);

		assertNotNull(endpoint);
	}
