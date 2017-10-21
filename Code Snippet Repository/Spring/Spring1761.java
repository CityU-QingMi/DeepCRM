	@Test
	public void noTrim() throws Exception {
		StringArrayPropertyEditor editor = new StringArrayPropertyEditor(",",false,false);
		editor.setAsText("  0,1  , 2 ");
		Object value = editor.getValue();
		String[] array = (String[]) value;
		for (int i = 0; i < array.length; ++i) {
			assertEquals(3, array[i].length());
			assertEquals("" + i, array[i].trim());
		}
		assertEquals("  0,1  , 2 ", editor.getAsText());
	}
