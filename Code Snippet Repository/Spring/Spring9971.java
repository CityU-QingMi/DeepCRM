	@Test
	public void invalidIfModifiedSinceHeader() {
		headers.set(HttpHeaders.IF_MODIFIED_SINCE, "0");
		assertEquals(-1, headers.getIfModifiedSince());

		headers.set(HttpHeaders.IF_MODIFIED_SINCE, "-1");
		assertEquals(-1, headers.getIfModifiedSince());

		headers.set(HttpHeaders.IF_MODIFIED_SINCE, "XXX");
		assertEquals(-1, headers.getIfModifiedSince());
	}
