	@Test
	public void setHtmlAndJavaScriptEscapeTrue() throws JspException {
		tag.setValue("url/path");
		tag.setVar("var");
		tag.setHtmlEscape(true);
		tag.setJavaScriptEscape(true);
		tag.doStartTag();

		Param param = new Param();
		param.setName("n me");
		param.setValue("v&l=e");
		tag.addParam(param);

		param = new Param();
		param.setName("name");
		param.setValue("value2");
		tag.addParam(param);

		tag.doEndTag();
		assertEquals("url\\/path?n%20me=v%26l%3De&amp;name=value2", context.getAttribute("var"));
	}
