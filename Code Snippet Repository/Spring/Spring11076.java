	@Test
	public void extractUriTemplateVarsRegexQualifiers() {
		PathPatternParser pp = new PathPatternParser();

		PathPattern p = pp.parse("{symbolicName:[\\p{L}\\.]+}-sources-{version:[\\p{N}\\.]+}.jar");
		PathPattern.PathMatchInfo result = p.matchAndExtract(toPathContainer("com.example-sources-1.0.0.jar"));
		assertEquals("com.example", result.getUriVariables().get("symbolicName"));
		assertEquals("1.0.0", result.getUriVariables().get("version"));

		p = pp.parse("{symbolicName:[\\w\\.]+}-sources-{version:[\\d\\.]+}-{year:\\d{4}}{month:\\d{2}}{day:\\d{2}}.jar");
		result = matchAndExtract(p,"com.example-sources-1.0.0-20100220.jar");
		assertEquals("com.example", result.getUriVariables().get("symbolicName"));
		assertEquals("1.0.0", result.getUriVariables().get("version"));
		assertEquals("2010", result.getUriVariables().get("year"));
		assertEquals("02", result.getUriVariables().get("month"));
		assertEquals("20", result.getUriVariables().get("day"));

		p = pp.parse("{symbolicName:[\\p{L}\\.]+}-sources-{version:[\\p{N}\\.\\{\\}]+}.jar");
		result = matchAndExtract(p, "com.example-sources-1.0.0.{12}.jar");
		assertEquals("com.example", result.getUriVariables().get("symbolicName"));
		assertEquals("1.0.0.{12}", result.getUriVariables().get("version"));
	}
