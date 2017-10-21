	@Test
	public void escapeBodyWithHtmlEscapeAndJavaScriptEscape() throws JspException {
		PageContext pc = createPageContext();
		final StringBuffer result = new StringBuffer();
		EscapeBodyTag tag = new EscapeBodyTag() {
			@Override
			protected String readBodyContent() {
				return "' test & text \\";
			}
			@Override
			protected void writeBodyContent(String content) {
				result.append(content);
			}
		};
		tag.setPageContext(pc);
		tag.setHtmlEscape(true);
		tag.setJavaScriptEscape(true);
		assertEquals(BodyTag.EVAL_BODY_BUFFERED, tag.doStartTag());
		assertEquals(Tag.SKIP_BODY, tag.doAfterBody());
		assertEquals("Correct content", "&#39; test &amp; text \\\\", result.toString());
	}
