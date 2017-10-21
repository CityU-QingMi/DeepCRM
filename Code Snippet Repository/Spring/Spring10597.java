	@Test
	public void checkModifiedETag() {
		String currentETag = "\"Foo\"";
		String oldEtag = "Bar";
		servletRequest.addHeader("If-None-Match", oldEtag);

		assertFalse(request.checkNotModified(currentETag));

		assertEquals(200, servletResponse.getStatus());
		assertEquals(currentETag, servletResponse.getHeader("ETag"));
	}
