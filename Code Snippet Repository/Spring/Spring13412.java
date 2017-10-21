	@Test
	public void replaceUriTemplateParamsTemplateWithParamMatchNamePreEncoding() throws JspException {
		List<Param> params = new LinkedList<>();
		Set<String> usedParams = new HashSet<>();

		Param param = new Param();
		param.setName("n me");
		param.setValue("value");
		params.add(param);

		String uri = tag.replaceUriTemplateParams("url/{n me}", params, usedParams);
		assertEquals("url/value", uri);
		assertEquals(1, usedParams.size());
		assertTrue(usedParams.contains("n me"));
	}
