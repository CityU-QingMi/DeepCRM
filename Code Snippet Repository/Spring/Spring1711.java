	@Test
	public void testLocaleEditor() {
		PropertyEditor localeEditor = new LocaleEditor();
		localeEditor.setAsText("en_CA");
		assertEquals(Locale.CANADA, localeEditor.getValue());
		assertEquals("en_CA", localeEditor.getAsText());

		localeEditor = new LocaleEditor();
		assertEquals("", localeEditor.getAsText());
	}
