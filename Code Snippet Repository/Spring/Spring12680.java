	@Test
	public void requestMappingHandlerAdapter() throws Exception {
		ApplicationContext context = initContext(WebConfig.class);
		RequestMappingHandlerAdapter adapter = context.getBean(RequestMappingHandlerAdapter.class);
		List<HttpMessageConverter<?>> converters = adapter.getMessageConverters();
		assertEquals(12, converters.size());
		converters.stream()
				.filter(converter -> converter instanceof AbstractJackson2HttpMessageConverter)
				.forEach(converter -> {
					ObjectMapper mapper = ((AbstractJackson2HttpMessageConverter) converter).getObjectMapper();
					assertFalse(mapper.getDeserializationConfig().isEnabled(DEFAULT_VIEW_INCLUSION));
					assertFalse(mapper.getSerializationConfig().isEnabled(DEFAULT_VIEW_INCLUSION));
					assertFalse(mapper.getDeserializationConfig().isEnabled(FAIL_ON_UNKNOWN_PROPERTIES));
					if (converter instanceof MappingJackson2XmlHttpMessageConverter) {
						assertEquals(XmlMapper.class, mapper.getClass());
					}
				});

		ConfigurableWebBindingInitializer initializer =
				(ConfigurableWebBindingInitializer) adapter.getWebBindingInitializer();
		assertNotNull(initializer);

		ConversionService conversionService = initializer.getConversionService();
		assertNotNull(conversionService);
		assertTrue(conversionService instanceof FormattingConversionService);

		Validator validator = initializer.getValidator();
		assertNotNull(validator);
		assertTrue(validator instanceof LocalValidatorFactoryBean);

		DirectFieldAccessor fieldAccessor = new DirectFieldAccessor(adapter);
		@SuppressWarnings("unchecked")
		List<Object> bodyAdvice = (List<Object>) fieldAccessor.getPropertyValue("requestResponseBodyAdvice");
		assertEquals(2, bodyAdvice.size());
		assertEquals(JsonViewRequestBodyAdvice.class, bodyAdvice.get(0).getClass());
		assertEquals(JsonViewResponseBodyAdvice.class, bodyAdvice.get(1).getClass());
	}
