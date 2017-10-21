	@Test
	public void testPathMatchingConfiguration() {
		loadBeanDefinitions("mvc-config-path-matching.xml");
		RequestMappingHandlerMapping hm = this.appContext.getBean(RequestMappingHandlerMapping.class);
		assertNotNull(hm);
		assertTrue(hm.useSuffixPatternMatch());
		assertFalse(hm.useTrailingSlashMatch());
		assertTrue(hm.useRegisteredSuffixPatternMatch());
		assertThat(hm.getUrlPathHelper(), Matchers.instanceOf(TestPathHelper.class));
		assertThat(hm.getPathMatcher(), Matchers.instanceOf(TestPathMatcher.class));
		List<String> fileExtensions = hm.getContentNegotiationManager().getAllFileExtensions();
		assertThat(fileExtensions, Matchers.contains("xml"));
		assertThat(fileExtensions, Matchers.hasSize(1));
	}
