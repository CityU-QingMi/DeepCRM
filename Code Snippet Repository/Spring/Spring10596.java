	@Test
	public void checkNotModifiedETagWithSeparatorChars() {
		String eTag = "\"Foo, Bar\"";
		servletRequest.addHeader("If-None-Match", eTag);

		assertTrue(request.checkNotModified(eTag));

		assertEquals(304, servletResponse.getStatus());
		assertEquals(eTag, servletResponse.getHeader("ETag"));
	}
