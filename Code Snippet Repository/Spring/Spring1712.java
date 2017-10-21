	@Test
	public void testPatternEditor() {
		final String REGEX = "a.*";

		PropertyEditor patternEditor = new PatternEditor();
		patternEditor.setAsText(REGEX);
		assertEquals(Pattern.compile(REGEX).pattern(), ((Pattern) patternEditor.getValue()).pattern());
		assertEquals(REGEX, patternEditor.getAsText());

		patternEditor = new PatternEditor();
		assertEquals("", patternEditor.getAsText());

		patternEditor = new PatternEditor();
		patternEditor.setAsText(null);
		assertEquals("", patternEditor.getAsText());
	}
