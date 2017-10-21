	@Test
	public void random() throws Throwable {
		// TODO: fix Reactor support

		RestTemplate restTemplate = new RestTemplate();

		byte[] body = randomBytes();
		RequestEntity<byte[]> request = RequestEntity.post(new URI("http://localhost:" + port)).body(body);
		ResponseEntity<byte[]> response = restTemplate.exchange(request, byte[].class);

		assertNotNull(response.getBody());
		assertEquals(RESPONSE_SIZE,
				response.getHeaders().getContentLength());
		assertEquals(RESPONSE_SIZE, response.getBody().length);
	}
