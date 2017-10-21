	@Test
	public void createQueryStringParamNullName() throws JspException {
		List<Param> params = new LinkedList<>();
		Set<String> usedParams = new HashSet<>();

		Param param = new Param();
		param.setName(null);
		param.setValue("value");
		params.add(param);

		String queryString = tag.createQueryString(params, usedParams, true);
		assertEquals("", queryString);
	}
