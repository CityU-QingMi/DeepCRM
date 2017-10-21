	@Test
	public void createBinderWithGlobalInitialization() throws Exception {
		ConversionService conversionService = new DefaultFormattingConversionService();
		bindingInitializer.setConversionService(conversionService);

		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/").build());
		BindingContext context = createBindingContext("initBinder", WebDataBinder.class);
		WebDataBinder dataBinder = context.createDataBinder(exchange, null, null);

		assertSame(conversionService, dataBinder.getConversionService());
	}
