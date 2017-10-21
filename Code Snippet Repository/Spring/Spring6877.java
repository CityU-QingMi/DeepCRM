	@Override
	@Test
	public void jmsHandlerMethodFactoryConfiguration() throws JMSException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"annotation-driven-custom-handler-method-factory.xml", getClass());

		thrown.expect(ListenerExecutionFailedException.class);
		thrown.expectCause(Is.<MethodArgumentNotValidException>isA(MethodArgumentNotValidException.class));
		testJmsHandlerMethodFactoryConfiguration(context);
	}
