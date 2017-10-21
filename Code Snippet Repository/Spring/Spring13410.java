	@Test
	public void createQueryStringParamEmptyName() throws JspException {
		List<Param> params = new LinkedList<>();
		Set<String> usedParams = new HashSet<>();

		Param param = new Param();
		param.setName("");
		param.setValue("value");
		params.add(param);

		String queryString = tag.createQueryString(params, usedParams, true);
		assertEquals("", queryString);
	}
