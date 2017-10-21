	@Test
	public void testConvertToReferenceUTF8() {
		HtmlCharacterEntityReferences entityReferences = new HtmlCharacterEntityReferences();
		String utf8 = "UTF-8";
		assertEquals("&lt;", entityReferences.convertToReference('<', utf8));
		assertEquals("&gt;", entityReferences.convertToReference('>', utf8));
		assertEquals("&amp;", entityReferences.convertToReference('&', utf8));
		assertEquals("&quot;", entityReferences.convertToReference('"', utf8));
		assertEquals("&#39;", entityReferences.convertToReference('\'', utf8));
		assertNull(entityReferences.convertToReference((char) 233, utf8));
		assertNull(entityReferences.convertToReference((char) 934, utf8));
	}
