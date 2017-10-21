	@Test
	public void checkNotModifiedETagWeakStrong() {
		String eTag = "\"Foo\"";
		String weakEtag = String.format("W/%s", eTag);
		servletRequest.addHeader("If-None-Match", eTag);

		assertTrue(request.checkNotModified(weakEtag));

		assertEquals(304, servletResponse.getStatus());
		assertEquals(weakEtag, servletResponse.getHeader("ETag"));
	}
