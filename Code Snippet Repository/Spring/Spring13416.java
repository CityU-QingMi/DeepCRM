	@Test
	public void replaceUriTemplateParamsTemplateWithPath() throws JspException {
		List<Param> params = new LinkedList<>();
		Set<String> usedParams = new HashSet<>();

		Param param = new Param();
		param.setName("name");
		param.setValue("my/Id");
		params.add(param);

		String uri = tag.replaceUriTemplateParams("url/{name}", params, usedParams);
		assertEquals("url/my/Id", uri);
		assertEquals(1, usedParams.size());
		assertTrue(usedParams.contains("name"));
	}
