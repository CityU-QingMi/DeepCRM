	@Test
	public void expiredSessionIsRecreated() throws Exception {

		// First request: no session yet, new session created
		RequestEntity<Void> request = RequestEntity.get(createUri()).build();
		ResponseEntity<Void> response = this.restTemplate.exchange(request, Void.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		String id = extractSessionId(response.getHeaders());
		assertNotNull(id);
		assertEquals(1, this.handler.getSessionRequestCount());

		// Second request: same session
		request = RequestEntity.get(createUri()).header("Cookie", "SESSION=" + id).build();
		response = this.restTemplate.exchange(request, Void.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNull(response.getHeaders().get("Set-Cookie"));
		assertEquals(2, this.handler.getSessionRequestCount());

		// Now fast-forward by 31 minutes
		InMemoryWebSessionStore store = (InMemoryWebSessionStore) this.sessionManager.getSessionStore();
		WebSession session = store.retrieveSession(id).block();
		assertNotNull(session);
		store.setClock(Clock.offset(store.getClock(), Duration.ofMinutes(31)));

		// Third request: expired session, new session created
		request = RequestEntity.get(createUri()).header("Cookie", "SESSION=" + id).build();
		response = this.restTemplate.exchange(request, Void.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		id = extractSessionId(response.getHeaders());
		assertNotNull("Expected new session id", id);
		assertEquals(1, this.handler.getSessionRequestCount());
	}
