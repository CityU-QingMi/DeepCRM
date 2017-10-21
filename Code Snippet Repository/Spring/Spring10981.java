	@Test
	public void testEncodeIntoHtmlCharacterSetFromUtf8() {
		String utf8 = ("UTF-8");
		assertEquals("An empty string should be converted to an empty string",
				"", HtmlUtils.htmlEscape("", utf8));
		assertEquals("A string containing no special characters should not be affected",
				"A sentence containing no special characters.",
				HtmlUtils.htmlEscape("A sentence containing no special characters."));

		assertEquals("'< >' should be encoded to '&lt; &gt;'",
				"&lt; &gt;", HtmlUtils.htmlEscape("< >", utf8));
		assertEquals("'< >' should be encoded to '&#60; &#62;'",
				"&#60; &#62;", HtmlUtils.htmlEscapeDecimal("< >", utf8));

		assertEquals("UTF-8 supported chars should not be escaped",
				"Μερικοί Ελληνικοί &quot;χαρακτήρες&quot;",
				HtmlUtils.htmlEscape("Μερικοί Ελληνικοί \"χαρακτήρες\"", utf8));
	}
