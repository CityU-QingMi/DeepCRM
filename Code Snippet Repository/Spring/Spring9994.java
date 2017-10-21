	@Test
	public void headersCopyWithEmptyAndNull() {
		ResponseEntity<Void> responseEntityWithEmptyHeaders =
				ResponseEntity.ok().headers(new HttpHeaders()).build();
		ResponseEntity<Void> responseEntityWithNullHeaders =
				ResponseEntity.ok().headers(null).build();

		assertEquals(HttpStatus.OK, responseEntityWithEmptyHeaders.getStatusCode());
		assertTrue(responseEntityWithEmptyHeaders.getHeaders().isEmpty());
		assertEquals(responseEntityWithEmptyHeaders.toString(), responseEntityWithNullHeaders.toString());
	}
