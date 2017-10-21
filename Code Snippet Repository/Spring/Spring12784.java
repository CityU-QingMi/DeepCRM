	@Test
	public void matchSuffixPatternUsingFileExtensions2() {
		PatternsRequestCondition condition1 = new PatternsRequestCondition(
				new String[] {"/prefix"}, null, null, true, false, Arrays.asList("json"));

		PatternsRequestCondition condition2 = new PatternsRequestCondition(
				new String[] {"/suffix"}, null, null, true, false, null);

		PatternsRequestCondition combined = condition1.combine(condition2);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/prefix/suffix.json");
		PatternsRequestCondition match = combined.getMatchingCondition(request);

		assertNotNull(match);
	}
