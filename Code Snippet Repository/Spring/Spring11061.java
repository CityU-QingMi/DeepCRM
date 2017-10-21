	@Test
	public void captureTheRestPatterns() {
		pathPattern = parse("{*foobar}");
		assertEquals("/{*foobar}", pathPattern.computePatternString());
		assertPathElements(pathPattern, CaptureTheRestPathElement.class);
		pathPattern = checkStructure("/{*foobar}");
		assertPathElements(pathPattern, CaptureTheRestPathElement.class);
		checkError("/{*foobar}/", 10, PatternMessage.NO_MORE_DATA_EXPECTED_AFTER_CAPTURE_THE_REST);
		checkError("/{*foobar}abc", 10, PatternMessage.NO_MORE_DATA_EXPECTED_AFTER_CAPTURE_THE_REST);
		checkError("/{*f%obar}", 4, PatternMessage.ILLEGAL_CHARACTER_IN_CAPTURE_DESCRIPTOR);
		checkError("/{*foobar}abc", 10, PatternMessage.NO_MORE_DATA_EXPECTED_AFTER_CAPTURE_THE_REST);
		checkError("/{f*oobar}", 3, PatternMessage.ILLEGAL_CHARACTER_IN_CAPTURE_DESCRIPTOR);
		checkError("/{*foobar}/abc", 10, PatternMessage.NO_MORE_DATA_EXPECTED_AFTER_CAPTURE_THE_REST);
		checkError("/{*foobar:.*}/abc", 9, PatternMessage.ILLEGAL_CHARACTER_IN_CAPTURE_DESCRIPTOR);
		checkError("/{abc}{*foobar}", 1, PatternMessage.CAPTURE_ALL_IS_STANDALONE_CONSTRUCT);
		checkError("/{abc}{*foobar}{foo}", 15, PatternMessage.NO_MORE_DATA_EXPECTED_AFTER_CAPTURE_THE_REST);
	}
