	@Override
	protected void extendRequest(MockHttpServletRequest request) {
		TestBean bean = new TestBean();
		bean.setName("foo");
		bean.setCountry("UK");
		bean.setMyFloat(new Float("12.34"));
		request.setAttribute(COMMAND_NAME, bean);

		List floats = new ArrayList();
		floats.add(new Float("12.30"));
		floats.add(new Float("12.31"));
		floats.add(new Float("12.32"));
		floats.add(new Float("12.33"));
		floats.add(new Float("12.34"));
		floats.add(new Float("12.35"));

		request.setAttribute("floats", floats);
	}
