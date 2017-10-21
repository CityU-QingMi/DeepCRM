	@Test
	public void forward() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/myservlet/handler.do");
		request.setContextPath("/mycontext");
		request.setServletPath("/myservlet");
		request.setPathInfo(";mypathinfo");
		request.setQueryString("?param1=value1");

		view.setUrl(url);
		view.setServletContext(new MockServletContext() {
			@Override
			public int getMinorVersion() {
				return 4;
			}
		});

		view.render(model, request, response);
		assertEquals(url, response.getForwardedUrl());

		model.forEach((key, value) -> assertEquals("Values for model key '" + key
				+ "' must match", value, request.getAttribute(key)));
	}
