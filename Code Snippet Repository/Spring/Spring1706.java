	@Test
	public void testCharacterEditorGetAsTextReturnsEmptyStringIfValueIsNull() throws Exception {
		PropertyEditor charEditor = new CharacterEditor(false);
		assertEquals("", charEditor.getAsText());
		charEditor = new CharacterEditor(true);
		charEditor.setAsText(null);
		assertEquals("", charEditor.getAsText());
		charEditor.setAsText("");
		assertEquals("", charEditor.getAsText());
		charEditor.setAsText(" ");
		assertEquals(" ", charEditor.getAsText());
	}
