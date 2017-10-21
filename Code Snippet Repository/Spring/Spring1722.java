	@Test
	public void testStringTrimmerEditorWithCharsToDeleteAndEmptyAsNull() {
		StringTrimmerEditor editor = new StringTrimmerEditor("\r\n\f", true);
		editor.setAsText("te\ns\ft");
		assertEquals("test", editor.getValue());
		assertEquals("test", editor.getAsText());
		editor.setAsText(" test ");
		assertEquals("test", editor.getValue());
		assertEquals("test", editor.getAsText());
		editor.setAsText(" \n\f ");
		assertEquals(null, editor.getValue());
		assertEquals("", editor.getAsText());
		editor.setValue(null);
		assertEquals("", editor.getAsText());
	}
