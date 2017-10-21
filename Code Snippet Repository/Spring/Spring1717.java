	@Test
	public void testCustomNumberEditorWithEmptyAsNull() {
		CustomNumberEditor editor = new CustomNumberEditor(Integer.class, true);
		editor.setAsText("5");
		assertEquals(new Integer(5), editor.getValue());
		assertEquals("5", editor.getAsText());
		editor.setAsText("");
		assertEquals(null, editor.getValue());
		assertEquals("", editor.getAsText());
		editor.setValue(null);
		assertEquals(null, editor.getValue());
		assertEquals("", editor.getAsText());
	}
