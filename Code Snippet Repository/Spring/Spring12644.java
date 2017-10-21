	@Test
	public void configureMessageConverters() {
		final HttpMessageConverter customConverter = mock(HttpMessageConverter.class);
		final StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
		List<WebMvcConfigurer> configurers = new ArrayList<>();
		configurers.add(new WebMvcConfigurer() {
			@Override
			public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
				converters.add(stringConverter);
			}
			@Override
			public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
				converters.add(0, customConverter);
			}
		});
		delegatingConfig = new DelegatingWebMvcConfiguration();
		delegatingConfig.setConfigurers(configurers);

		RequestMappingHandlerAdapter adapter = delegatingConfig.requestMappingHandlerAdapter();
		assertEquals("Only one custom converter should be registered", 2, adapter.getMessageConverters().size());
		assertSame(customConverter, adapter.getMessageConverters().get(0));
		assertSame(stringConverter, adapter.getMessageConverters().get(1));
	}
