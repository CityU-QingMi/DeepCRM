	@Test
	public void createUrlLocalContext() throws JspException {
		((MockHttpServletRequest) context.getRequest()).setContextPath("/app-context");

		tag.setValue("/url/path");
		tag.doStartTag();

		String uri = tag.createUrl();
		assertEquals("/app-context/url/path", uri);
	}
