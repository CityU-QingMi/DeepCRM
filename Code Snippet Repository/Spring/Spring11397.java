	@Test
	public void customMessageConverterConfig() throws Exception {
		ApplicationContext context = loadConfig(CustomMessageConverterConfig.class);

		String name = "requestMappingHandlerAdapter";
		RequestMappingHandlerAdapter adapter = context.getBean(name, RequestMappingHandlerAdapter.class);
		assertNotNull(adapter);

		List<HttpMessageReader<?>> messageReaders = adapter.getMessageReaders();
		assertEquals(2, messageReaders.size());

		assertHasMessageReader(messageReaders, forClass(String.class), TEXT_PLAIN);
		assertHasMessageReader(messageReaders, forClass(TestBean.class), APPLICATION_XML);
	}
