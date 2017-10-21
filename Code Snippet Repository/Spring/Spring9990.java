	@Test
	public void createdLocation() throws URISyntaxException {
		URI location = new URI("location");
		ResponseEntity<Void> responseEntity = ResponseEntity.created(location).build();

		assertNotNull(responseEntity);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertTrue(responseEntity.getHeaders().containsKey("Location"));
		assertEquals(location.toString(),
				responseEntity.getHeaders().getFirst("Location"));
		assertNull(responseEntity.getBody());

		ResponseEntity.created(location).header("MyResponseHeader", "MyValue").body("Hello World");
	}
