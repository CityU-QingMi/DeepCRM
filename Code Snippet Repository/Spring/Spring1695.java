	@Test
	public void sunnyDaySetAsText() throws Exception {
		final String text = "Hideous towns make me throw... up";
		charEditor.setAsText(text);

		Object value = charEditor.getValue();
		assertNotNull(value);
		assertTrue(value instanceof char[]);
		char[] chars = (char[]) value;
		for (int i = 0; i < text.length(); ++i) {
			assertEquals("char[] differs at index '" + i + "'", text.charAt(i), chars[i]);
		}
		assertEquals(text, charEditor.getAsText());
	}
