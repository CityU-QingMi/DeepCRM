	@Test
	public void createUrlWithTemplateParams() throws JspException {
		tag.setValue("url/{name}");
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
		assertEquals("url/value?n%20me=v%20lue", uri);
	}
