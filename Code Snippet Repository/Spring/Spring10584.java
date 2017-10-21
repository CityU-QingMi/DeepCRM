	@Test
	public void checkModifiedETagAndNotModifiedTimestamp() throws Exception {
		String currentETag = "\"Foo\"";
		String oldEtag = "\"Bar\"";
		servletRequest.addHeader("If-None-Match", oldEtag);
		long epochTime = currentDate.getTime();
		servletRequest.addHeader("If-Modified-Since", epochTime);

		assertFalse(request.checkNotModified(currentETag, epochTime));

		assertEquals(200, servletResponse.getStatus());
		assertEquals(currentETag, servletResponse.getHeader("ETag"));
		assertEquals(RFC_1123_DATE_TIME.format(Instant.ofEpochMilli(epochTime).atZone(GMT)), servletResponse.getHeader("Last-Modified"));
	}
