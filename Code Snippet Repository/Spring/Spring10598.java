	@Test
	public void checkNotModifiedUnpaddedETag() {
		String eTag = "Foo";
		String paddedEtag = String.format("\"%s\"", eTag);
		servletRequest.addHeader("If-None-Match", paddedEtag);

		assertTrue(request.checkNotModified(eTag));

		assertEquals(304, servletResponse.getStatus());
		assertEquals(paddedEtag, servletResponse.getHeader("ETag"));
	}
