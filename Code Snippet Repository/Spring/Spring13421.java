	@Test
	public void createUrlWithParams() throws JspException {
		tag.setValue("url/path");
		tag.doStartTag();

		Param param = new Param();
		param.setName("name");
		param.setValue("value");
		tag.addParam(param);

		param = new Param();
		param.setName("n me");
		param.setValue("v lue");
		tag.addParam(param);

		String uri = tag.createUrl();
		assertEquals("url/path?name=value&n%20me=v%20lue", uri);
	}
