	@Test
	public void testDecodeFromHtmlCharacterSet() {
		assertEquals("An empty string should be converted to an empty string",
				"", HtmlUtils.htmlUnescape(""));
		assertEquals("A string containing no special characters should not be affected",
				"This is a sentence containing no special characters.",
				HtmlUtils.htmlUnescape("This is a sentence containing no special characters."));

		assertEquals("'A&nbsp;B' should be decoded to 'A B'",
				"A" + (char) 160 + "B", HtmlUtils.htmlUnescape("A&nbsp;B"));

		assertEquals("'&lt; &gt;' should be decoded to '< >'",
				"< >", HtmlUtils.htmlUnescape("&lt; &gt;"));
		assertEquals("'&#60; &#62;' should be decoded to '< >'",
				"< >", HtmlUtils.htmlUnescape("&#60; &#62;"));

		assertEquals("'&#x41;&#X42;&#x43;' should be decoded to 'ABC'",
				"ABC", HtmlUtils.htmlUnescape("&#x41;&#X42;&#x43;"));

		assertEquals("'&phi;' should be decoded to uni-code character 966",
				"" + (char) 966, HtmlUtils.htmlUnescape("&phi;"));

		assertEquals("'&Prime;' should be decoded to uni-code character 8243",
				"" + (char) 8243, HtmlUtils.htmlUnescape("&Prime;"));

		assertEquals("A not supported named reference leads should be ingnored",
				"&prIme;", HtmlUtils.htmlUnescape("&prIme;"));

		assertEquals("An empty reference '&;' should be survive the decoding",
				"&;", HtmlUtils.htmlUnescape("&;"));

		assertEquals("The longest character entity reference '&thetasym;' should be processable",
				"" + (char) 977, HtmlUtils.htmlUnescape("&thetasym;"));

		assertEquals("A malformed decimal reference should survive the decoding",
				"&#notADecimalNumber;", HtmlUtils.htmlUnescape("&#notADecimalNumber;"));
		assertEquals("A malformed hex reference should survive the decoding",
				"&#XnotAHexNumber;", HtmlUtils.htmlUnescape("&#XnotAHexNumber;"));

		assertEquals("The numerical reference '&#1;' should be converted to char 1",
				"" + (char) 1, HtmlUtils.htmlUnescape("&#1;"));

		assertEquals("The malformed hex reference '&#x;' should remain '&#x;'",
				"&#x;", HtmlUtils.htmlUnescape("&#x;"));
	}
