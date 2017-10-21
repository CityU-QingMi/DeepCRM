	@Test
	public void checkNotModifiedETagStrongWeak() {
		String eTag = "\"Foo\"";
		servletRequest.addHeader("If-None-Match", String.format("W/%s", eTag));

		assertTrue(request.checkNotModified(eTag));

		assertEquals(304, servletResponse.getStatus());
		assertEquals(eTag, servletResponse.getHeader("ETag"));
	}
