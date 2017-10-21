	@Test
	public void defaultPathMatchConfiguration() throws Exception {
		ApplicationContext context = initContext(WebConfig.class);
		UrlPathHelper urlPathHelper = context.getBean(UrlPathHelper.class);
		PathMatcher pathMatcher = context.getBean(PathMatcher.class);

		assertNotNull(urlPathHelper);
		assertNotNull(pathMatcher);
		assertEquals(AntPathMatcher.class, pathMatcher.getClass());
	}
