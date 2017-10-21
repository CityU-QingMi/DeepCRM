	@Test
	public void writeOnly() throws Exception {
		RestTemplate restTemplate = new RestTemplate();

		this.body = randomBytes();
		RequestEntity<byte[]> request = RequestEntity.post(
				new URI("http://localhost:" + port)).body(
						"".getBytes(StandardCharsets.UTF_8));
		ResponseEntity<byte[]> response = restTemplate.exchange(request, byte[].class);

		assertArrayEquals(body, response.getBody());
	}
