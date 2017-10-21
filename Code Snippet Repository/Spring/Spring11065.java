	@Test
	public void illegalCapturePatterns() {
		checkError("{abc/", 4, PatternMessage.MISSING_CLOSE_CAPTURE);
		checkError("{abc:}/", 5, PatternMessage.MISSING_REGEX_CONSTRAINT);
		checkError("{", 1, PatternMessage.MISSING_CLOSE_CAPTURE);
		checkError("{abc", 4, PatternMessage.MISSING_CLOSE_CAPTURE);
		checkError("{/}", 1, PatternMessage.MISSING_CLOSE_CAPTURE);
		checkError("/{", 2, PatternMessage.MISSING_CLOSE_CAPTURE);
		checkError("}", 0, PatternMessage.MISSING_OPEN_CAPTURE);
		checkError("/}", 1, PatternMessage.MISSING_OPEN_CAPTURE);
		checkError("def}", 3, PatternMessage.MISSING_OPEN_CAPTURE);
		checkError("/{/}", 2, PatternMessage.MISSING_CLOSE_CAPTURE);
		checkError("/{{/}", 2, PatternMessage.ILLEGAL_NESTED_CAPTURE);
		checkError("/{abc{/}", 5, PatternMessage.ILLEGAL_NESTED_CAPTURE);
		checkError("/{0abc}/abc", 2, PatternMessage.ILLEGAL_CHARACTER_AT_START_OF_CAPTURE_DESCRIPTOR);
		checkError("/{a?bc}/abc", 3, PatternMessage.ILLEGAL_CHARACTER_IN_CAPTURE_DESCRIPTOR);
		checkError("/{abc}_{abc}", 1, PatternMessage.ILLEGAL_DOUBLE_CAPTURE);
		checkError("/foobar/{abc}_{abc}", 8, PatternMessage.ILLEGAL_DOUBLE_CAPTURE);
		checkError("/foobar/{abc:..}_{abc:..}", 8, PatternMessage.ILLEGAL_DOUBLE_CAPTURE);
		PathPattern pp = parse("/{abc:foo(bar)}");
		try {
			pp.matchAndExtract(toPSC("/foo"));
			fail("Should have raised exception");
		}
		catch (IllegalArgumentException iae) {
			assertEquals("No capture groups allowed in the constraint regex: foo(bar)", iae.getMessage());
		}
		try {
			pp.matchAndExtract(toPSC("/foobar"));
			fail("Should have raised exception");
		}
		catch (IllegalArgumentException iae) {
			assertEquals("No capture groups allowed in the constraint regex: foo(bar)", iae.getMessage());
		}
	}
