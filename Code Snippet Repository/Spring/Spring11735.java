	@Test
	public void createBinderTypeConversion() throws Exception {
		MockServerHttpRequest request = MockServerHttpRequest.get("/path?requestParam=22").build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);
		ReactiveAdapterRegistry adapterRegistry = new ReactiveAdapterRegistry();
		this.argumentResolvers.add(new RequestParamMethodArgumentResolver(null, adapterRegistry, false));

		BindingContext context = createBindingContext("initBinderTypeConversion", WebDataBinder.class, int.class);
		WebDataBinder dataBinder = context.createDataBinder(exchange, null, "foo");

		assertNotNull(dataBinder.getDisallowedFields());
		assertEquals("requestParam-22", dataBinder.getDisallowedFields()[0]);
	}
