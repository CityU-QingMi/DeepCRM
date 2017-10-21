	@SuppressWarnings("")
	@Test
	public void initBinderMethod() throws Exception {

		Validator validator = mock(Validator.class);

		TestController controller = new TestController();
		controller.setValidator(validator);
		InitBinderBindingContext context = getBindingContext(controller);

		Method method = ResolvableMethod.on(TestController.class).annotPresent(GetMapping.class).resolveMethod();
		HandlerMethod handlerMethod = new HandlerMethod(controller, method);
		this.modelInitializer.initModel(handlerMethod, context, this.exchange).block(Duration.ofMillis(5000));

		WebExchangeDataBinder binder = context.createDataBinder(this.exchange, "name");
		assertEquals(Collections.singletonList(validator), binder.getValidators());
	}
