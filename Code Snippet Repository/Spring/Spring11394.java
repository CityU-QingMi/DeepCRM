	@Test
	public void requestMappingHandlerMapping() throws Exception {
		ApplicationContext context = loadConfig(WebFluxConfig.class);
		final Field trailingSlashField = ReflectionUtils.findField(PathPatternParser.class, "matchOptionalTrailingSeparator");
		ReflectionUtils.makeAccessible(trailingSlashField);

		String name = "requestMappingHandlerMapping";
		RequestMappingHandlerMapping mapping = context.getBean(name, RequestMappingHandlerMapping.class);
		assertNotNull(mapping);

		assertEquals(0, mapping.getOrder());

		assertNotNull(mapping.getPathPatternParser());
		boolean matchOptionalTrailingSlash = (boolean) ReflectionUtils
				.getField(trailingSlashField, mapping.getPathPatternParser());
		assertTrue(matchOptionalTrailingSlash);

		name = "webFluxContentTypeResolver";
		RequestedContentTypeResolver resolver = context.getBean(name, RequestedContentTypeResolver.class);
		assertSame(resolver, mapping.getContentTypeResolver());

		ServerWebExchange exchange = MockServerWebExchange.from(get("/path").accept(MediaType.APPLICATION_JSON).build());
		assertEquals(Collections.singletonList(MediaType.APPLICATION_JSON), resolver.resolveMediaTypes(exchange));
	}
