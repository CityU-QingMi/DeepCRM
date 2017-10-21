	@Test
	public void getRequestRemoveSemicolonContent() throws UnsupportedEncodingException {
		helper.setRemoveSemicolonContent(true);

		request.setRequestURI("/foo;f=F;o=O;o=O/bar;b=B;a=A;r=R");
		assertEquals("/foo/bar", helper.getRequestUri(request));

		// SPR-13455

		request.setServletPath("/foo/1");
		request.setRequestURI("/foo/;test/1");

		assertEquals("/foo/1", helper.getRequestUri(request));
	}
