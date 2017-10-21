	@Test
	public void createQueryStringTwoParams() throws JspException {
		List<Param> params = new LinkedList<>();
		Set<String> usedParams = new HashSet<>();

		Param param = new Param();
		param.setName("name");
		param.setValue("value");
		params.add(param);

		param = new Param();
		param.setName("name");
		param.setValue("value2");
		params.add(param);

		String queryString = tag.createQueryString(params, usedParams, true);
		assertEquals("?name=value&name=value2", queryString);
	}
