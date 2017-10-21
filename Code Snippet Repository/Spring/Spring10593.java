	@Test
	public void checkNotModifiedTimestamp() throws Exception {
		long epochTime = currentDate.getTime();
		servletRequest.addHeader("If-Modified-Since", epochTime);

		assertTrue(request.checkNotModified(epochTime));

		assertEquals(304, servletResponse.getStatus());
		assertEquals(RFC_1123_DATE_TIME.format(Instant.ofEpochMilli(epochTime).atZone(GMT)), servletResponse.getHeader("Last-Modified"));
	}
