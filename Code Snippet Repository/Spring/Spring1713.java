	@Test
	public void testCustomBooleanEditor() {
		CustomBooleanEditor editor = new CustomBooleanEditor(false);

		editor.setAsText("true");
		assertEquals(Boolean.TRUE, editor.getValue());
		assertEquals("true", editor.getAsText());

		editor.setAsText("false");
		assertEquals(Boolean.FALSE, editor.getValue());
		assertEquals("false", editor.getAsText());

		editor.setValue(null);
		assertEquals(null, editor.getValue());
		assertEquals("", editor.getAsText());

		try {
			editor.setAsText(null);
			fail("Should have thrown IllegalArgumentException");
		}
		catch (IllegalArgumentException ex) {
			// expected
		}
	}
