	@Test
	public void parameterAnnotationWithCglibProxy() throws JMSException {
		ProxyFactory pf = new ProxyFactory(sample);
		pf.setProxyTargetClass(true);
		listener = (JmsEndpointSampleBean) pf.getProxy();

		containerFactory.setMessageConverter(new UpperCaseMessageConverter());

		MethodJmsListenerEndpoint endpoint = createDefaultMethodJmsEndpoint(
				JmsEndpointSampleBean.class, "handleIt", String.class, String.class);
		Message message = new StubTextMessage("foo-bar");
		message.setStringProperty("my-header", "my-value");

		invokeListener(endpoint, message);
		assertListenerMethodInvocation("handleIt");
	}
