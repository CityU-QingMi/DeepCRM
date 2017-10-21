	@Test
	public void testCustomBooleanEditorWithSpecialTrueAndFalseStrings() throws Exception {
		String trueString = "pechorin";
		String falseString = "nash";

		CustomBooleanEditor editor = new CustomBooleanEditor(trueString, falseString, false);

		editor.setAsText(trueString);
		assertTrue(((Boolean) editor.getValue()).booleanValue());
		assertEquals(trueString, editor.getAsText());
		editor.setAsText(falseString);
		assertFalse(((Boolean) editor.getValue()).booleanValue());
		assertEquals(falseString, editor.getAsText());

		editor.setAsText(trueString.toUpperCase());
		assertTrue(((Boolean) editor.getValue()).booleanValue());
		assertEquals(trueString, editor.getAsText());
		editor.setAsText(falseString.toUpperCase());
		assertFalse(((Boolean) editor.getValue()).booleanValue());
		assertEquals(falseString, editor.getAsText());

		try {
			editor.setAsText(null);
			fail("Should have thrown IllegalArgumentException");
		}
		catch (IllegalArgumentException ex) {
			// expected
		}
	}
