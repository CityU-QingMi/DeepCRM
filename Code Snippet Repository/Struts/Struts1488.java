	public void testMatch() {
		
		WildcardHelper wild = new WildcardHelper();
		HashMap<String, String> matchedPatterns = new HashMap<>();
		int[] pattern = wild.compilePattern("wes-rules");
		assertEquals(wild.match(matchedPatterns,"wes-rules", pattern), true);
		assertEquals(wild.match(matchedPatterns, "rules-wes", pattern), false);
		
		pattern = wild.compilePattern("wes-*");
		assertEquals(wild.match(matchedPatterns,"wes-rules", pattern), true);
		assertEquals("rules".equals(matchedPatterns.get("1")), true);
		assertEquals(wild.match(matchedPatterns, "rules-wes", pattern), false);
		
		pattern = wild.compilePattern("path/**/file");
		assertEquals(wild.match(matchedPatterns, "path/to/file", pattern), true);
		assertEquals("to".equals(matchedPatterns.get("1")), true);
		assertEquals(wild.match(matchedPatterns, "path/to/another/location/of/file", pattern), true);
		assertEquals("to/another/location/of".equals(matchedPatterns.get("1")), true);
		
		pattern = wild.compilePattern("path/*/file");
		assertEquals(wild.match(matchedPatterns, "path/to/file", pattern), true);
		assertEquals("to".equals(matchedPatterns.get("1")), true);
		assertEquals(wild.match(matchedPatterns, "path/to/another/location/of/file", pattern), false);

		pattern = wild.compilePattern("path/*/another/**/file");
		assertEquals(wild.match(matchedPatterns, "path/to/another/location/of/file", pattern), true);
		assertEquals("to".equals(matchedPatterns.get("1")), true);
		assertEquals("location/of".equals(matchedPatterns.get("2")), true);
	}
