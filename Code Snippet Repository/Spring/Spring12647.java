	@Test
	public void configurePathMatch() throws Exception {
		final PathMatcher pathMatcher = mock(PathMatcher.class);
		final UrlPathHelper pathHelper = mock(UrlPathHelper.class);

		List<WebMvcConfigurer> configurers = new ArrayList<>();
		configurers.add(new WebMvcConfigurer() {
			@Override
			public void configurePathMatch(PathMatchConfigurer configurer) {
				configurer.setUseRegisteredSuffixPatternMatch(true)
						.setUseTrailingSlashMatch(false)
						.setUrlPathHelper(pathHelper)
						.setPathMatcher(pathMatcher);
			}
		});
		delegatingConfig.setConfigurers(configurers);

		RequestMappingHandlerMapping handlerMapping = delegatingConfig.requestMappingHandlerMapping();
		assertNotNull(handlerMapping);
		assertEquals("PathMatchConfigurer should configure RegisteredSuffixPatternMatch",
				true, handlerMapping.useRegisteredSuffixPatternMatch());
		assertEquals("PathMatchConfigurer should configure SuffixPatternMatch",
				true, handlerMapping.useSuffixPatternMatch());
		assertEquals("PathMatchConfigurer should configure TrailingSlashMatch",
				false, handlerMapping.useTrailingSlashMatch());
		assertEquals("PathMatchConfigurer should configure UrlPathHelper",
				pathHelper, handlerMapping.getUrlPathHelper());
		assertEquals("PathMatchConfigurer should configure PathMatcher",
				pathMatcher, handlerMapping.getPathMatcher());
	}
