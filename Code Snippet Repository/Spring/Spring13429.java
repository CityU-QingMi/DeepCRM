	@Test
	public void createQueryStringOneParamForExsistingQueryString() throws JspException {
		List<Param> params = new LinkedList<>();
		Set<String> usedParams = new HashSet<>();

		Param param = new Param();
		param.setName("name");
		param.setValue("value");
		params.add(param);

		String queryString = tag.createQueryString(params, usedParams, false);
		assertEquals("&name=value", queryString);
	}
