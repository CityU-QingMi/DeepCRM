	@Test
	public void compare_spr15597() {
		PathPatternParser parser = new PathPatternParser();
		PathPattern p1 = parser.parse("/{foo}");
		PathPattern p2 = parser.parse("/{foo}.*");
		PathPattern.PathMatchInfo r1 = matchAndExtract(p1, "/file.txt");
		PathPattern.PathMatchInfo r2 = matchAndExtract(p2, "/file.txt");
		 
		// works fine
		assertEquals("file.txt", r1.getUriVariables().get("foo"));
		assertEquals("file", r2.getUriVariables().get("foo"));

		// This produces 2 (see comments in https://jira.spring.io/browse/SPR-14544 )
		// Comparator<String> patternComparator = new AntPathMatcher().getPatternComparator("");
		// System.out.println(patternComparator.compare("/{foo}","/{foo}.*"));

		assertThat(p1.compareTo(p2), Matchers.greaterThan(0));
	}
