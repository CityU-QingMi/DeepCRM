	@Test
	public void headers() throws URISyntaxException {
		MediaType accept = MediaType.TEXT_PLAIN;
		long ifModifiedSince = 12345L;
		String ifNoneMatch = "\"foo\"";
		long contentLength = 67890;
		MediaType contentType = MediaType.TEXT_PLAIN;

		RequestEntity<Void> responseEntity = RequestEntity.post(new URI("http://example.com")).
				accept(accept).
				acceptCharset(StandardCharsets.UTF_8).
				ifModifiedSince(ifModifiedSince).
				ifNoneMatch(ifNoneMatch).
				contentLength(contentLength).
				contentType(contentType).
				build();

		assertNotNull(responseEntity);
		assertEquals(HttpMethod.POST, responseEntity.getMethod());
		assertEquals(new URI("http://example.com"), responseEntity.getUrl());
		HttpHeaders responseHeaders = responseEntity.getHeaders();

		assertEquals("text/plain", responseHeaders.getFirst("Accept"));
		assertEquals("utf-8", responseHeaders.getFirst("Accept-Charset"));
		assertEquals("Thu, 1 Jan 1970 00:00:12 GMT", responseHeaders.getFirst("If-Modified-Since"));
		assertEquals(ifNoneMatch, responseHeaders.getFirst("If-None-Match"));
		assertEquals(String.valueOf(contentLength), responseHeaders.getFirst("Content-Length"));
		assertEquals(contentType.toString(), responseHeaders.getFirst("Content-Type"));

		assertNull(responseEntity.getBody());
	}
