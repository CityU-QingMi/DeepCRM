	@Test
	public void normal() {
		String headerName = "My-Custom-Header";
		String headerValue1 = "HeaderValue1";
		String headerValue2 = "HeaderValue2";
		Integer entity = 42;

		ResponseEntity<Integer> responseEntity =
				ResponseEntity.status(HttpStatus.OK).header(headerName, headerValue1, headerValue2).body(entity);

		assertNotNull(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue(responseEntity.getHeaders().containsKey(headerName));
		List<String> list = responseEntity.getHeaders().get(headerName);
		assertEquals(2, list.size());
		assertEquals(headerValue1, list.get(0));
		assertEquals(headerValue2, list.get(1));
		assertEquals(entity, responseEntity.getBody());
	}
