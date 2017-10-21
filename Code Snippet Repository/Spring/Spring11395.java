	@Test
	public void customPathMatchConfig() throws Exception {
		ApplicationContext context = loadConfig(CustomPatchMatchConfig.class);
		final Field trailingSlashField = ReflectionUtils.findField(PathPatternParser.class, "matchOptionalTrailingSeparator");
		ReflectionUtils.makeAccessible(trailingSlashField);

		String name = "requestMappingHandlerMapping";
		RequestMappingHandlerMapping mapping = context.getBean(name, RequestMappingHandlerMapping.class);
		assertNotNull(mapping);
		assertNotNull(mapping.getPathPatternParser());

		boolean matchOptionalTrailingSlash = (boolean) ReflectionUtils
				.getField(trailingSlashField, mapping.getPathPatternParser());
		assertFalse(matchOptionalTrailingSlash);
	}
