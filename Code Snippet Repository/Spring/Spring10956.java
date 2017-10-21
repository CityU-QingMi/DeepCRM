	@Test
	public void invalidate() throws Exception {

		// First request: no session yet, new session created
		RequestEntity<Void> request = RequestEntity.get(createUri()).build();
		ResponseEntity<Void> response = this.restTemplate.exchange(request, Void.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		String id = extractSessionId(response.getHeaders());
		assertNotNull(id);

		// Second request: invalidates session
		URI uri = new URI("http://localhost:" + this.port + "/?invalidate");
		request = RequestEntity.get(uri).header("Cookie", "SESSION=" + id).build();
		response = this.restTemplate.exchange(request, Void.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		String value = response.getHeaders().getFirst("Set-Cookie");
		assertNotNull(value);
		assertTrue("Actual value: " + value, value.contains("Max-Age=0"));
	}
