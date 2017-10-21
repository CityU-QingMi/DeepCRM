	private void assertConditionalResponse(HttpStatus status, String body, String etag, long lastModified) throws Exception {
		assertEquals(status.value(), servletResponse.getStatus());
		assertTrue(mavContainer.isRequestHandled());
		if (body != null) {
			assertResponseBody(body);
		}
		else {
			assertEquals(0, servletResponse.getContentAsByteArray().length);
		}
		if (etag != null) {
			assertEquals(1, servletResponse.getHeaderValues(HttpHeaders.ETAG).size());
			assertEquals(etag, servletResponse.getHeader(HttpHeaders.ETAG));
		}
		if (lastModified != -1) {
			assertEquals(1, servletResponse.getHeaderValues(HttpHeaders.LAST_MODIFIED).size());
			assertEquals(RFC_1123_DATE_TIME.format(ofEpochMilli(lastModified).atZone(GMT)), servletResponse.getHeader(HttpHeaders.LAST_MODIFIED));
		}
	}
