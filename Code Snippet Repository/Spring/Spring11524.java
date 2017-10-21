	@Test
	public void flux() throws Exception {
		ParameterizedTypeReference<List<Person>> reference = new ParameterizedTypeReference<List<Person>>() {};
		ResponseEntity<List<Person>> result =
				restTemplate.exchange("http://localhost:" + port + "/flux", HttpMethod.GET, null, reference);

		assertEquals(HttpStatus.OK, result.getStatusCode());
		List<Person> body = result.getBody();
		assertEquals(2, body.size());
		assertEquals("John", body.get(0).getName());
		assertEquals("Jane", body.get(1).getName());
	}
