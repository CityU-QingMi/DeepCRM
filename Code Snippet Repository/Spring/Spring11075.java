	@Test
	public void extractUriTemplateVariablesRegex() {
		PathPatternParser pp = new PathPatternParser();
		PathPattern p = null;

		p = pp.parse("{symbolicName:[\\w\\.]+}-{version:[\\w\\.]+}.jar");
		PathPattern.PathMatchInfo result = matchAndExtract(p, "com.example-1.0.0.jar");
		assertEquals("com.example", result.getUriVariables().get("symbolicName"));
		assertEquals("1.0.0", result.getUriVariables().get("version"));

		p = pp.parse("{symbolicName:[\\w\\.]+}-sources-{version:[\\w\\.]+}.jar");
		result = matchAndExtract(p, "com.example-sources-1.0.0.jar");
		assertEquals("com.example", result.getUriVariables().get("symbolicName"));
		assertEquals("1.0.0", result.getUriVariables().get("version"));
	}
