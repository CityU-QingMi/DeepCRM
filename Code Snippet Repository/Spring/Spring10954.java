	@Test
	public void expiredSessionEnds() throws Exception {

		// First request: no session yet, new session created
		RequestEntity<Void> request = RequestEntity.get(createUri()).build();
		ResponseEntity<Void> response = this.restTemplate.exchange(request, Void.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		String id = extractSessionId(response.getHeaders());
		assertNotNull(id);

		// Now fast-forward by 31 minutes
		InMemoryWebSessionStore store = (InMemoryWebSessionStore) this.sessionManager.getSessionStore();
		store.setClock(Clock.offset(store.getClock(), Duration.ofMinutes(31)));

		// Second request: session expires
		URI uri = new URI("http://localhost:" + this.port + "/?expire");
		request = RequestEntity.get(uri).header("Cookie", "SESSION=" + id).build();
		response = this.restTemplate.exchange(request, Void.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		String value = response.getHeaders().getFirst("Set-Cookie");
		assertNotNull(value);
		assertTrue("Actual value: " + value, value.contains("Max-Age=0"));
	}
