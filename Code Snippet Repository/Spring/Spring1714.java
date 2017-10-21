	@Test
	public void testCustomBooleanEditorWithEmptyAsNull() {
		CustomBooleanEditor editor = new CustomBooleanEditor(true);

		editor.setAsText("true");
		assertEquals(Boolean.TRUE, editor.getValue());
		assertEquals("true", editor.getAsText());

		editor.setAsText("false");
		assertEquals(Boolean.FALSE, editor.getValue());
		assertEquals("false", editor.getAsText());

		editor.setValue(null);
		assertEquals(null, editor.getValue());
		assertEquals("", editor.getAsText());
	}
