	@Test
	public void varExplicitScope() throws JspException {
		tag.setValue("url/path");
		tag.setVar("var");
		tag.setScope("request");
		tag.doStartTag();
		tag.doEndTag();

		assertEquals("url/path", context.getAttribute("var", PageContext.REQUEST_SCOPE));
	}
