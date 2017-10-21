	@Override
	@Test
	public void jmsHandlerMethodFactoryConfiguration() throws JMSException {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(
				EnableJmsHandlerMethodFactoryConfig.class, ValidationBean.class);

		thrown.expect(ListenerExecutionFailedException.class);
		thrown.expectCause(Is.<MethodArgumentNotValidException>isA(MethodArgumentNotValidException.class));
		testJmsHandlerMethodFactoryConfiguration(context);
	}
