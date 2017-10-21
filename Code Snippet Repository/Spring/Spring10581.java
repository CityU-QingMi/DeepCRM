	@Test
	public void checkNotModifiedWildcardIsIgnored() {
		String eTag = "\"Foo\"";
		servletRequest.addHeader("If-None-Match", "*");

		assertFalse(request.checkNotModified(eTag));

		assertEquals(200, servletResponse.getStatus());
		assertEquals(eTag, servletResponse.getHeader("ETag"));
	}
