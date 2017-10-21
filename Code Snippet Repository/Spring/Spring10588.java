	@Test
	public void checkNotModifiedTimestampWithLengthPart() throws Exception {
		long epochTime = ZonedDateTime.parse(CURRENT_TIME, RFC_1123_DATE_TIME).toInstant().toEpochMilli();
		servletRequest.setMethod("GET");
		servletRequest.addHeader("If-Modified-Since", "Wed, 09 Apr 2014 09:57:42 GMT; length=13774");

		assertTrue(request.checkNotModified(epochTime));

		assertEquals(304, servletResponse.getStatus());
		assertEquals(RFC_1123_DATE_TIME.format(Instant.ofEpochMilli(epochTime).atZone(GMT)), servletResponse.getHeader("Last-Modified"));
	}
