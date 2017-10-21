	@Test
	public void initBinderAdvice() throws Exception {
		ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
		RequestMappingHandlerAdapter adapter = createAdapter(context);
		TestController controller = context.getBean(TestController.class);

		Validator validator = mock(Validator.class);
		controller.setValidator(validator);

		BindingContext bindingContext = handle(adapter, controller, "handle").getBindingContext();

		WebExchangeDataBinder binder = bindingContext.createDataBinder(this.exchange, "name");
		assertEquals(Collections.singletonList(validator), binder.getValidators());
	}
