	@Test
	public void requestMappingHandlerAdapter() throws Exception {
		ApplicationContext context = loadConfig(WebFluxConfig.class);

		String name = "requestMappingHandlerAdapter";
		RequestMappingHandlerAdapter adapter = context.getBean(name, RequestMappingHandlerAdapter.class);
		assertNotNull(adapter);

		List<HttpMessageReader<?>> readers = adapter.getMessageReaders();
		assertEquals(12, readers.size());

		assertHasMessageReader(readers, forClass(byte[].class), APPLICATION_OCTET_STREAM);
		assertHasMessageReader(readers, forClass(ByteBuffer.class), APPLICATION_OCTET_STREAM);
		assertHasMessageReader(readers, forClass(String.class), TEXT_PLAIN);
		assertHasMessageReader(readers, forClass(Resource.class), IMAGE_PNG);
		assertHasMessageReader(readers, forClassWithGenerics(MultiValueMap.class, String.class, String.class), APPLICATION_FORM_URLENCODED);
		assertHasMessageReader(readers, forClass(TestBean.class), APPLICATION_XML);
		assertHasMessageReader(readers, forClass(TestBean.class), APPLICATION_JSON);
		assertHasMessageReader(readers, forClass(TestBean.class), new MediaType("application", "x-jackson-smile"));
		assertHasMessageReader(readers, forClass(TestBean.class), null);

		WebBindingInitializer bindingInitializer = adapter.getWebBindingInitializer();
		assertNotNull(bindingInitializer);
		WebExchangeDataBinder binder = new WebExchangeDataBinder(new Object());
		bindingInitializer.initBinder(binder);

		name = "webFluxConversionService";
		ConversionService service = context.getBean(name, ConversionService.class);
		assertSame(service, binder.getConversionService());

		name = "webFluxValidator";
		Validator validator = context.getBean(name, Validator.class);
		assertSame(validator, binder.getValidator());
	}
