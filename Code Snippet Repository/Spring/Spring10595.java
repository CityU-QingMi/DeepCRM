	@Test
	public void checkNotModifiedETag() {
		String eTag = "\"Foo\"";
		servletRequest.addHeader("If-None-Match", eTag);

		assertTrue(request.checkNotModified(eTag));

		assertEquals(304, servletResponse.getStatus());
		assertEquals(eTag, servletResponse.getHeader("ETag"));
	}
