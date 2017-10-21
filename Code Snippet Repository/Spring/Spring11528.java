	@Test
	public void filter() throws Exception {
		ResponseEntity<String> result =
				restTemplate.getForEntity("http://localhost:" + port + "/filter", String.class);

		assertEquals(HttpStatus.OK, result.getStatusCode());
		Map<String, String> body = parseBody(result.getBody());
		assertEquals(3, body.size());
		assertEquals("foo", body.get("name"));
		assertEquals("baz", body.get("bar"));
		assertEquals("quux", body.get("qux"));
	}
