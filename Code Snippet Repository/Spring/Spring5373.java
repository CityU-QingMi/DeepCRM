	@Test
	public void extractUriTemplateVarsRegexQualifiers() {
		Map<String, String> result = pathMatcher.extractUriTemplateVariables(
				"{symbolicName:[\\p{L}\\.]+}-sources-{version:[\\p{N}\\.]+}.jar",
				"com.example-sources-1.0.0.jar");
		assertEquals("com.example", result.get("symbolicName"));
		assertEquals("1.0.0", result.get("version"));

		result = pathMatcher.extractUriTemplateVariables(
				"{symbolicName:[\\w\\.]+}-sources-{version:[\\d\\.]+}-{year:\\d{4}}{month:\\d{2}}{day:\\d{2}}.jar",
				"com.example-sources-1.0.0-20100220.jar");
		assertEquals("com.example", result.get("symbolicName"));
		assertEquals("1.0.0", result.get("version"));
		assertEquals("2010", result.get("year"));
		assertEquals("02", result.get("month"));
		assertEquals("20", result.get("day"));

		result = pathMatcher.extractUriTemplateVariables(
				"{symbolicName:[\\p{L}\\.]+}-sources-{version:[\\p{N}\\.\\{\\}]+}.jar",
				"com.example-sources-1.0.0.{12}.jar");
		assertEquals("com.example", result.get("symbolicName"));
		assertEquals("1.0.0.{12}", result.get("version"));
	}
