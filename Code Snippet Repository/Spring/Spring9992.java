	@Test
	public void Etagheader() throws URISyntaxException {

		ResponseEntity<Void> responseEntity = ResponseEntity.ok().eTag("\"foo\"").build();
		assertEquals("\"foo\"", responseEntity.getHeaders().getETag());

		responseEntity = ResponseEntity.ok().eTag("foo").build();
		assertEquals("\"foo\"", responseEntity.getHeaders().getETag());

		responseEntity = ResponseEntity.ok().eTag("W/\"foo\"").build();
		assertEquals("W/\"foo\"", responseEntity.getHeaders().getETag());
	}
