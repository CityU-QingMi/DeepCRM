	@Test
	public void configurerConsumers() throws Exception {

		TestConsumer<ArgumentResolverConfigurer> argumentResolverConsumer = new TestConsumer<>();
		TestConsumer<RequestedContentTypeResolverBuilder> contenTypeResolverConsumer = new TestConsumer<>();
		TestConsumer<CorsRegistry> corsRegistryConsumer = new TestConsumer<>();
		TestConsumer<FormatterRegistry> formatterConsumer = new TestConsumer<>();
		TestConsumer<ServerCodecConfigurer> codecsConsumer = new TestConsumer<>();
		TestConsumer<PathMatchConfigurer> pathMatchingConsumer = new TestConsumer<>();
		TestConsumer<ViewResolverRegistry> viewResolverConsumer = new TestConsumer<>();

		new DefaultControllerSpec(new MyController())
				.argumentResolvers(argumentResolverConsumer)
				.contentTypeResolver(contenTypeResolverConsumer)
				.corsMappings(corsRegistryConsumer)
				.formatters(formatterConsumer)
				.httpMessageCodecs(codecsConsumer)
				.pathMatching(pathMatchingConsumer)
				.viewResolvers(viewResolverConsumer)
				.build();

		assertNotNull(argumentResolverConsumer.getValue());
		assertNotNull(contenTypeResolverConsumer.getValue());
		assertNotNull(corsRegistryConsumer.getValue());
		assertNotNull(formatterConsumer.getValue());
		assertNotNull(codecsConsumer.getValue());
		assertNotNull(pathMatchingConsumer.getValue());
		assertNotNull(viewResolverConsumer.getValue());

	}
