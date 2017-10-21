	@Test
	public void withCustomSeparator() throws Exception {
		StringArrayPropertyEditor editor = new StringArrayPropertyEditor(":");
		editor.setAsText("0:1:2");
		Object value = editor.getValue();
		assertTrue(value instanceof String[]);
		String[] array = (String[]) value;
		for (int i = 0; i < array.length; ++i) {
			assertEquals("" + i, array[i]);
		}
		assertEquals("0:1:2", editor.getAsText());
	}
