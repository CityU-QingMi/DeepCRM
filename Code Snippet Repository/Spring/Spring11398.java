	@Test
	public void responseEntityResultHandler() throws Exception {
		ApplicationContext context = loadConfig(WebFluxConfig.class);

		String name = "responseEntityResultHandler";
		ResponseEntityResultHandler handler = context.getBean(name, ResponseEntityResultHandler.class);
		assertNotNull(handler);

		assertEquals(0, handler.getOrder());

		List<HttpMessageWriter<?>> writers = handler.getMessageWriters();
		assertEquals(10, writers.size());

		assertHasMessageWriter(writers, forClass(byte[].class), APPLICATION_OCTET_STREAM);
		assertHasMessageWriter(writers, forClass(ByteBuffer.class), APPLICATION_OCTET_STREAM);
		assertHasMessageWriter(writers, forClass(String.class), TEXT_PLAIN);
		assertHasMessageWriter(writers, forClass(Resource.class), IMAGE_PNG);
		assertHasMessageWriter(writers, forClass(TestBean.class), APPLICATION_XML);
		assertHasMessageWriter(writers, forClass(TestBean.class), APPLICATION_JSON);
		assertHasMessageWriter(writers, forClass(TestBean.class), new MediaType("application", "x-jackson-smile"));
		assertHasMessageWriter(writers, forClass(TestBean.class), MediaType.parseMediaType("text/event-stream"));

		name = "webFluxContentTypeResolver";
		RequestedContentTypeResolver resolver = context.getBean(name, RequestedContentTypeResolver.class);
		assertSame(resolver, handler.getContentTypeResolver());
	}
