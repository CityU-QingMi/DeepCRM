	@Test
	public void printJavaScriptEscapedAttributeResult() throws Exception {
		tag.setExpression("bean.js()");
		tag.setJavaScriptEscape(true);
		int action = tag.doStartTag();
		assertEquals(Tag.EVAL_BODY_INCLUDE, action);
		action = tag.doEndTag();
		assertEquals(Tag.EVAL_PAGE, action);
		assertEquals("function foo() { alert(\\\"hi\\\") }",
				((MockHttpServletResponse)context.getResponse()).getContentAsString());
	}
