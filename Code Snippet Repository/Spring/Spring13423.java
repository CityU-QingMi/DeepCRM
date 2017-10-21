	@Test
	public void createUrlWithParamAndExistingQueryString() throws JspException {
		tag.setValue("url/path?foo=bar");
		tag.doStartTag();

		Param param = new Param();
		param.setName("name");
		param.setValue("value");
		tag.addParam(param);

		String uri = tag.createUrl();
		assertEquals("url/path?foo=bar&name=value", uri);
	}
