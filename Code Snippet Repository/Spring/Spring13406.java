	@Test
	public void createQueryStringOneParamAlreadyUsed() throws JspException {
		List<Param> params = new LinkedList<>();
		Set<String> usedParams = new HashSet<>();

		Param param = new Param();
		param.setName("name");
		param.setValue("value");
		params.add(param);
		usedParams.add("name");

		String queryString = tag.createQueryString(params, usedParams, true);
		assertEquals("", queryString);
	}
