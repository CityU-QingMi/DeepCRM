	@Test
	public void checkNotModifiedETagAndModifiedTimestamp() {
		String eTag = "\"Foo\"";
		servletRequest.addHeader("If-None-Match", eTag);
		long currentEpoch = currentDate.getTime();
		long oneMinuteAgo = currentEpoch - (1000 * 60);
		servletRequest.addHeader("If-Modified-Since", oneMinuteAgo);

		assertTrue(request.checkNotModified(eTag, currentEpoch));

		assertEquals(304, servletResponse.getStatus());
		assertEquals(eTag, servletResponse.getHeader("ETag"));
		assertEquals(RFC_1123_DATE_TIME.format(Instant.ofEpochMilli(currentEpoch).atZone(GMT)), servletResponse.getHeader("Last-Modified"));
	}
