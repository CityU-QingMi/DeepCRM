	@Test
	public void matchSuffixPatternUsingFileExtensions() {
		String[] patterns = new String[] {"/jobs/{jobName}"};
		List<String> extensions = Arrays.asList("json");
		PatternsRequestCondition condition = new PatternsRequestCondition(patterns, null, null, true, false, extensions);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/jobs/my.job");
		PatternsRequestCondition match = condition.getMatchingCondition(request);

		assertNotNull(match);
		assertEquals("/jobs/{jobName}", match.getPatterns().iterator().next());

		request = new MockHttpServletRequest("GET", "/jobs/my.job.json");
		match = condition.getMatchingCondition(request);

		assertNotNull(match);
		assertEquals("/jobs/{jobName}.json", match.getPatterns().iterator().next());
	}
