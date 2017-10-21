	@Test
	public void parameterAnnotationWithJdkProxy() throws JMSException {
		ProxyFactory pf = new ProxyFactory(sample);
		listener = (JmsEndpointSampleInterface) pf.getProxy();

		containerFactory.setMessageConverter(new UpperCaseMessageConverter());

		MethodJmsListenerEndpoint endpoint = createDefaultMethodJmsEndpoint(
				JmsEndpointSampleInterface.class, "handleIt", String.class, String.class);
		Message message = new StubTextMessage("foo-bar");
		message.setStringProperty("my-header", "my-value");

		invokeListener(endpoint, message);
		assertListenerMethodInvocation("handleIt");
	}
