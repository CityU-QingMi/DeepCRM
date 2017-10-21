	@Test
	public void getRequestKeepSemicolonContent() throws UnsupportedEncodingException {
		helper.setRemoveSemicolonContent(false);

		request.setRequestURI("/foo;a=b;c=d");
		assertEquals("/foo;a=b;c=d", helper.getRequestUri(request));

		request.setRequestURI("/foo;jsessionid=c0o7fszeb1");
		assertEquals("jsessionid should always be removed", "/foo", helper.getRequestUri(request));

		request.setRequestURI("/foo;a=b;jsessionid=c0o7fszeb1;c=d");
		assertEquals("jsessionid should always be removed", "/foo;a=b;c=d", helper.getRequestUri(request));

		// SPR-10398

		request.setRequestURI("/foo;a=b;JSESSIONID=c0o7fszeb1;c=d");
		assertEquals("JSESSIONID should always be removed", "/foo;a=b;c=d", helper.getRequestUri(request));
	}
