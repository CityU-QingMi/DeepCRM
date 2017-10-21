	@Test
	public void createUrlRemoteContext() throws JspException {
		((MockHttpServletRequest) context.getRequest()).setContextPath("/app-context");

		tag.setValue("/url/path");
		tag.setContext("some-other-context");
		tag.doStartTag();

		String uri = tag.createUrl();
		assertEquals("/some-other-context/url/path", uri);
	}
