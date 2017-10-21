	@Test
	public void headers() throws URISyntaxException {
		URI location = new URI("location");
		long contentLength = 67890;
		MediaType contentType = MediaType.TEXT_PLAIN;

		ResponseEntity<Void> responseEntity = ResponseEntity.ok().
				allow(HttpMethod.GET).
				lastModified(12345L).
				location(location).
				contentLength(contentLength).
				contentType(contentType).
				build();

		assertNotNull(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		HttpHeaders responseHeaders = responseEntity.getHeaders();

		assertEquals("GET", responseHeaders.getFirst("Allow"));
		assertEquals("Thu, 1 Jan 1970 00:00:12 GMT",
				responseHeaders.getFirst("Last-Modified"));
		assertEquals(location.toASCIIString(),
				responseHeaders.getFirst("Location"));
		assertEquals(String.valueOf(contentLength), responseHeaders.getFirst("Content-Length"));
		assertEquals(contentType.toString(), responseHeaders.getFirst("Content-Type"));

		assertNull(responseEntity.getBody());
	}
