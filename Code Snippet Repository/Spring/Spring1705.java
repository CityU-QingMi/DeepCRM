	@Test
	public void testCharacterEditorWithAllowEmpty() {
		CharBean cb = new CharBean();
		BeanWrapper bw = new BeanWrapperImpl(cb);
		bw.registerCustomEditor(Character.class, new CharacterEditor(true));

		bw.setPropertyValue("myCharacter", new Character('c'));
		assertEquals(new Character('c'), cb.getMyCharacter());

		bw.setPropertyValue("myCharacter", "c");
		assertEquals(new Character('c'), cb.getMyCharacter());

		bw.setPropertyValue("myCharacter", "\u0041");
		assertEquals(new Character('A'), cb.getMyCharacter());

		bw.setPropertyValue("myCharacter", " ");
		assertEquals(new Character(' '), cb.getMyCharacter());

		bw.setPropertyValue("myCharacter", "");
		assertNull(cb.getMyCharacter());
	}
