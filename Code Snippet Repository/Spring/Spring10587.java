	@Test
	public void checkNotModifiedMultipleETags() {
		String eTag = "\"Bar\"";
		String multipleETags = String.format("\"Foo\", %s", eTag);
		servletRequest.addHeader("If-None-Match", multipleETags);

		assertTrue(request.checkNotModified(eTag));

		assertEquals(304, servletResponse.getStatus());
		assertEquals(eTag, servletResponse.getHeader("ETag"));
	}
