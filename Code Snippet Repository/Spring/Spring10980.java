	@Test
	public void testEncodeIntoHtmlCharacterSet() {
		assertEquals("An empty string should be converted to an empty string",
				"", HtmlUtils.htmlEscape(""));
		assertEquals("A string containing no special characters should not be affected",
				"A sentence containing no special characters.",
				HtmlUtils.htmlEscape("A sentence containing no special characters."));

		assertEquals("'< >' should be encoded to '&lt; &gt;'",
				"&lt; &gt;", HtmlUtils.htmlEscape("< >"));
		assertEquals("'< >' should be encoded to '&#60; &#62;'",
				"&#60; &#62;", HtmlUtils.htmlEscapeDecimal("< >"));

		assertEquals("The special character 8709 should be encoded to '&empty;'",
				"&empty;", HtmlUtils.htmlEscape("" + (char) 8709));
		assertEquals("The special character 8709 should be encoded to '&#8709;'",
				"&#8709;", HtmlUtils.htmlEscapeDecimal("" + (char) 8709));

		assertEquals("The special character 977 should be encoded to '&thetasym;'",
				"&thetasym;", HtmlUtils.htmlEscape("" + (char) 977));
		assertEquals("The special character 977 should be encoded to '&#977;'",
				"&#977;", HtmlUtils.htmlEscapeDecimal("" + (char) 977));
	}
