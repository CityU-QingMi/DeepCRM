	@Test
	public void testStringTrimmerEditorWithEmptyAsNull() {
		StringTrimmerEditor editor = new StringTrimmerEditor(true);
		editor.setAsText("test");
		assertEquals("test", editor.getValue());
		assertEquals("test", editor.getAsText());
		editor.setAsText(" test ");
		assertEquals("test", editor.getValue());
		assertEquals("test", editor.getAsText());
		editor.setAsText("  ");
		assertEquals(null, editor.getValue());
		assertEquals("", editor.getAsText());
		editor.setValue(null);
		assertEquals("", editor.getAsText());
	}
