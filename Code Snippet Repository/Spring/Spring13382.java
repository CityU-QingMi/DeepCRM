	@Test
	public void messageTagWithTextEncodingEscaped() throws JspException {
		PageContext pc = createPageContext();
		pc.getServletContext().setInitParameter(WebUtils.RESPONSE_ENCODED_HTML_ESCAPE_CONTEXT_PARAM, "true");
		pc.getResponse().setCharacterEncoding("UTF-8");
		final StringBuffer message = new StringBuffer();
		MessageTag tag = new MessageTag() {
			@Override
			protected void writeMessage(String msg) {
				message.append(msg);
			}
		};
		tag.setPageContext(pc);
		tag.setText("test <&> é");
		tag.setHtmlEscape(true);
		assertTrue("Correct doStartTag return value", tag.doStartTag() == Tag.EVAL_BODY_INCLUDE);
		assertEquals("Correct doEndTag return value", Tag.EVAL_PAGE, tag.doEndTag());
		assertEquals("Correct message", "test &lt;&amp;&gt; é", message.toString());
	}
