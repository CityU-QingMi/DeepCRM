	@Test
	public void handlingError() throws Exception {
		// TODO: fix Reactor
		assumeFalse(server instanceof ReactorHttpServer);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(NO_OP_ERROR_HANDLER);

		URI url = new URI("http://localhost:" + port + "/handling-error");
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
