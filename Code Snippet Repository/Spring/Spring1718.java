	@Test
	public void testStringTrimmerEditor() {
		StringTrimmerEditor editor = new StringTrimmerEditor(false);
		editor.setAsText("test");
		assertEquals("test", editor.getValue());
		assertEquals("test", editor.getAsText());
		editor.setAsText(" test ");
		assertEquals("test", editor.getValue());
		assertEquals("test", editor.getAsText());
		editor.setAsText("");
		assertEquals("", editor.getValue());
		assertEquals("", editor.getAsText());
		editor.setValue(null);
		assertEquals("", editor.getAsText());
		editor.setAsText(null);
		assertEquals("", editor.getAsText());
	}
