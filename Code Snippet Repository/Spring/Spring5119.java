	@Test
	public void synthesizeAnnotationWithArrayOfChars() throws Exception {
		CharsContainer charsContainer = GroupOfCharsClass.class.getAnnotation(CharsContainer.class);
		assertNotNull(charsContainer);
		CharsContainer synthesizedCharsContainer = synthesizeAnnotation(charsContainer);
		assertThat(synthesizedCharsContainer, instanceOf(SynthesizedAnnotation.class));

		char[] chars = synthesizedCharsContainer.chars();
		assertArrayEquals(new char[] { 'x', 'y', 'z' }, chars);

		// Alter array returned from synthesized annotation
		chars[0] = '?';

		// Re-retrieve the array from the synthesized annotation
		chars = synthesizedCharsContainer.chars();
		assertArrayEquals(new char[] { 'x', 'y', 'z' }, chars);
	}
