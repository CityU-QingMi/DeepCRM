	private void testException(Throwable exception, String expected) throws Exception {
		ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
		RequestMappingHandlerAdapter adapter = createAdapter(context);

		TestController controller = context.getBean(TestController.class);
		controller.setException(exception);

		Object actual = handle(adapter, controller, "handle").getReturnValue();
		assertEquals(expected, actual);
	}
