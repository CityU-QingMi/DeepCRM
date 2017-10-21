	@Test
	public void escapeBodyWithJavaScriptEscape() throws JspException {
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
		tag.setJavaScriptEscape(true);
		assertEquals(BodyTag.EVAL_BODY_BUFFERED, tag.doStartTag());
		assertEquals(Tag.SKIP_BODY, tag.doAfterBody());
		assertEquals("Correct content", "\\' test & text \\\\", result.toString());
	}
