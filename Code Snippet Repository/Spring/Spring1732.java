	@Test
	public void testCharsetEditor() throws Exception {
		CharsetEditor editor = new CharsetEditor();
		String name = "UTF-8";
		editor.setAsText(name);
		Charset charset = Charset.forName(name);
		assertEquals("Invalid Charset conversion", charset, editor.getValue());
		editor.setValue(charset);
		assertEquals("Invalid Charset conversion", name, editor.getAsText());
	}
