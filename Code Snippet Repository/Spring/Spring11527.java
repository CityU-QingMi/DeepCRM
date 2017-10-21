	@Test
	public void normal() throws Exception {
		ResponseEntity<String> result =
				restTemplate.getForEntity("http://localhost:" + port + "/normal", String.class);

		assertEquals(HttpStatus.OK, result.getStatusCode());
		Map<String, String> body = parseBody(result.getBody());
		assertEquals(2, body.size());
		assertEquals("foo", body.get("name"));
		assertEquals("baz", body.get("bar"));
	}
