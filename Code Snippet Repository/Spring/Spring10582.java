	@Test
	public void checkNotModifiedETagAndTimestamp() {
		String eTag = "\"Foo\"";
		servletRequest.addHeader("If-None-Match", eTag);
		servletRequest.addHeader("If-Modified-Since", currentDate.getTime());

		assertTrue(request.checkNotModified(eTag, currentDate.getTime()));

		assertEquals(304, servletResponse.getStatus());
		assertEquals(eTag, servletResponse.getHeader("ETag"));
		assertEquals(RFC_1123_DATE_TIME.format(currentDate.toInstant().atZone(GMT)), servletResponse.getHeader("Last-Modified"));
	}
