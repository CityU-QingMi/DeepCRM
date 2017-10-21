	@Test
	public void replaceUriTemplateParamsTemplateWithParamMatchValueEncoded() throws JspException {
		List<Param> params = new LinkedList<>();
		Set<String> usedParams = new HashSet<>();

		Param param = new Param();
		param.setName("name");
		param.setValue("v lue");
		params.add(param);

		String uri = tag.replaceUriTemplateParams("url/{name}", params,
				usedParams);

		assertEquals("url/v%20lue", uri);
		assertEquals(1, usedParams.size());
		assertTrue(usedParams.contains("name"));
	}
