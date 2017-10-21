	@SuppressWarnings("")
	@Test
	public void requestMappingHandlerAdapter() throws Exception {
		RequestMappingHandlerAdapter adapter = this.config.requestMappingHandlerAdapter();

		// ConversionService
		String actual = this.config.mvcConversionService().convert(new TestBean(), String.class);
		assertEquals("converted", actual);

		// Message converters
		List<HttpMessageConverter<?>> converters = adapter.getMessageConverters();
		assertEquals(2, converters.size());
		assertEquals(StringHttpMessageConverter.class, converters.get(0).getClass());
		assertEquals(MappingJackson2HttpMessageConverter.class, converters.get(1).getClass());
		ObjectMapper objectMapper = ((MappingJackson2HttpMessageConverter) converters.get(1)).getObjectMapper();
		assertFalse(objectMapper.getDeserializationConfig().isEnabled(DEFAULT_VIEW_INCLUSION));
		assertFalse(objectMapper.getSerializationConfig().isEnabled(DEFAULT_VIEW_INCLUSION));
		assertFalse(objectMapper.getDeserializationConfig().isEnabled(FAIL_ON_UNKNOWN_PROPERTIES));

		DirectFieldAccessor fieldAccessor = new DirectFieldAccessor(adapter);

		// Custom argument resolvers and return value handlers
		List<HandlerMethodArgumentResolver> argResolvers =
			(List<HandlerMethodArgumentResolver>) fieldAccessor.getPropertyValue("customArgumentResolvers");
		assertEquals(1, argResolvers.size());

		List<HandlerMethodReturnValueHandler> handlers =
			(List<HandlerMethodReturnValueHandler>) fieldAccessor.getPropertyValue("customReturnValueHandlers");
		assertEquals(1, handlers.size());

		// Async support options
		assertEquals(ConcurrentTaskExecutor.class, fieldAccessor.getPropertyValue("taskExecutor").getClass());
		assertEquals(2500L, fieldAccessor.getPropertyValue("asyncRequestTimeout"));

		CallableProcessingInterceptor[] callableInterceptors =
				(CallableProcessingInterceptor[]) fieldAccessor.getPropertyValue("callableInterceptors");
		assertEquals(1, callableInterceptors.length);

		DeferredResultProcessingInterceptor[] deferredResultInterceptors =
				(DeferredResultProcessingInterceptor[]) fieldAccessor.getPropertyValue("deferredResultInterceptors");
		assertEquals(1, deferredResultInterceptors.length);

		assertEquals(false, fieldAccessor.getPropertyValue("ignoreDefaultModelOnRedirect"));
	}
