	@Test
	public void changeSessionId() throws Exception {

		// First request: no session yet, new session created
		RequestEntity<Void> request = RequestEntity.get(createUri()).build();
		ResponseEntity<Void> response = this.restTemplate.exchange(request, Void.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		String oldId = extractSessionId(response.getHeaders());
		assertNotNull(oldId);
		assertEquals(1, this.handler.getSessionRequestCount());

		// Second request: session id changes
		URI uri = new URI("http://localhost:" + this.port + "/?changeId");
		request = RequestEntity.get(uri).header("Cookie", "SESSION=" + oldId).build();
		response = this.restTemplate.exchange(request, Void.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		String newId = extractSessionId(response.getHeaders());
		assertNotNull("Expected new session id", newId);
		assertNotEquals(oldId, newId);
		assertEquals(2, this.handler.getSessionRequestCount());
	}
