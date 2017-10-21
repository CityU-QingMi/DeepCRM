	@Test
	public void createUrlRemoteContextSingleSlash() throws JspException {
		((MockHttpServletRequest) context.getRequest()).setContextPath("/app-context");

		tag.setValue("/url/path");
		tag.setContext("/");
		tag.doStartTag();

		String uri = tag.createUrl();
		assertEquals("/url/path", uri);
	}
