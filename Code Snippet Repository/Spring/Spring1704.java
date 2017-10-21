	@Test
	public void testCharacterEditor() {
		CharBean cb = new CharBean();
		BeanWrapper bw = new BeanWrapperImpl(cb);

		bw.setPropertyValue("myChar", new Character('c'));
		assertEquals('c', cb.getMyChar());

		bw.setPropertyValue("myChar", "c");
		assertEquals('c', cb.getMyChar());

		bw.setPropertyValue("myChar", "\u0041");
		assertEquals('A', cb.getMyChar());

		bw.setPropertyValue("myChar", "\\u0022");
		assertEquals('"', cb.getMyChar());

		CharacterEditor editor = new CharacterEditor(false);
		editor.setAsText("M");
		assertEquals("M", editor.getAsText());
	}
