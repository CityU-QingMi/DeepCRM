	@Test
	public void replaceUriTemplateParamsTemplateWithParamMatch() throws JspException {
		List<Param> params = new LinkedList<>();
		Set<String> usedParams = new HashSet<>();

		Param param = new Param();
		param.setName("name");
		param.setValue("value");
		params.add(param);

		String uri = tag.replaceUriTemplateParams("url/{name}", params, usedParams);
		assertEquals("url/value", uri);
		assertEquals(1, usedParams.size());
		assertTrue(usedParams.contains("name"));
	}
