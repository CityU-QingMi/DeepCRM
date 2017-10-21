	@Test
	public void headersCopy() {
		HttpHeaders customHeaders = new HttpHeaders();
		customHeaders.set("X-CustomHeader", "vale");

		ResponseEntity<Void> responseEntity = ResponseEntity.ok().headers(customHeaders).build();
		HttpHeaders responseHeaders = responseEntity.getHeaders();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(1, responseHeaders.size());
		assertEquals(1, responseHeaders.get("X-CustomHeader").size());
		assertEquals("vale", responseHeaders.getFirst("X-CustomHeader"));

	}
