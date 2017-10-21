	@Test
	public void checkModifiedUnpaddedETag() {
		String currentETag = "Foo";
		String oldEtag = "Bar";
		servletRequest.addHeader("If-None-Match", oldEtag);

		assertFalse(request.checkNotModified(currentETag));

		assertEquals(200, servletResponse.getStatus());
		assertEquals(String.format("\"%s\"", currentETag), servletResponse.getHeader("ETag"));
	}
