	@Test
	public void createSession() throws Exception {
		RequestEntity<Void> request = RequestEntity.get(createUri()).build();
		ResponseEntity<Void> response = this.restTemplate.exchange(request, Void.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		String id = extractSessionId(response.getHeaders());
		assertNotNull(id);
		assertEquals(1, this.handler.getSessionRequestCount());

		request = RequestEntity.get(createUri()).header("Cookie", "SESSION=" + id).build();
		response = this.restTemplate.exchange(request, Void.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNull(response.getHeaders().get("Set-Cookie"));
		assertEquals(2, this.handler.getSessionRequestCount());
	}
