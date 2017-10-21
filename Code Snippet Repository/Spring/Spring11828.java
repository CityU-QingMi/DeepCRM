	@Test
	public void resource() throws Exception {
		ResponseEntity<byte[]> response = performGet("/resource", new HttpHeaders(), byte[].class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.hasBody());
		assertEquals(951, response.getHeaders().getContentLength());
		assertEquals(951, response.getBody().length);
		assertEquals(new MediaType("image", "png"), response.getHeaders().getContentType());
	}
