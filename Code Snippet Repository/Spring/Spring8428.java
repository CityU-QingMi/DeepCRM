	@SuppressWarnings("")
	private static void assertCacheContents(DefaultContextCache cache, String... expectedNames) {

		Map<MergedContextConfiguration, ApplicationContext> contextMap =
				(Map<MergedContextConfiguration, ApplicationContext>) ReflectionTestUtils.getField(cache, "contextMap");

		// @formatter:off
		List<String> actualNames = contextMap.keySet().stream()
			.map(cfg -> cfg.getClasses()[0])
			.map(Class::getSimpleName)
			.collect(toList());
		// @formatter:on

		assertEquals(asList(expectedNames), actualNames);
	}
