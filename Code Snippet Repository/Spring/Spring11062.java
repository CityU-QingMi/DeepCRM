	@Test
	public void equalsAndHashcode() {
		PathPatternParser caseInsensitiveParser = new PathPatternParser();
		caseInsensitiveParser.setCaseSensitive(false);
		PathPatternParser caseSensitiveParser = new PathPatternParser();
		PathPattern pp1 = caseInsensitiveParser.parse("/abc");
		PathPattern pp2 = caseInsensitiveParser.parse("/abc");
		PathPattern pp3 = caseInsensitiveParser.parse("/def");
		assertEquals(pp1, pp2);
		assertEquals(pp1.hashCode(), pp2.hashCode());
		assertNotEquals(pp1, pp3);
		assertFalse(pp1.equals("abc"));

		pp1 = caseInsensitiveParser.parse("/abc");
		pp2 = caseSensitiveParser.parse("/abc");
		assertFalse(pp1.equals(pp2));
		assertNotEquals(pp1.hashCode(), pp2.hashCode());
	}
