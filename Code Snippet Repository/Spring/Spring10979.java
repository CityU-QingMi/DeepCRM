	@Test
	public void testHtmlEscape() {
		String unescaped = "\"This is a quote'";
		String escaped = HtmlUtils.htmlEscape(unescaped);
		assertEquals("&quot;This is a quote&#39;", escaped);
		escaped = HtmlUtils.htmlEscapeDecimal(unescaped);
		assertEquals("&#34;This is a quote&#39;", escaped);
		escaped = HtmlUtils.htmlEscapeHex(unescaped);
		assertEquals("&#x22;This is a quote&#x27;", escaped);
	}
