	@Test
	public void checkModifiedTimestamp() {
		long oneMinuteAgo = currentDate.getTime() - (1000 * 60);
		servletRequest.addHeader("If-Modified-Since", oneMinuteAgo);

		assertFalse(request.checkNotModified(currentDate.getTime()));

		assertEquals(200, servletResponse.getStatus());
		assertEquals(RFC_1123_DATE_TIME.format(currentDate.toInstant().atZone(GMT)), servletResponse.getHeader("Last-Modified"));
	}
