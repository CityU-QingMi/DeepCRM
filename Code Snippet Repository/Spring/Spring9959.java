	@Test
	public void responseEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		String body = "foo";
		HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);
		ResponseEntity<String> responseEntity = new ResponseEntity<>(body, headers, HttpStatus.OK);
		ResponseEntity<String> responseEntity2 = new ResponseEntity<>(body, headers, HttpStatus.OK);

		assertEquals(body, responseEntity.getBody());
		assertEquals(MediaType.TEXT_PLAIN, responseEntity.getHeaders().getContentType());
		assertEquals("text/plain", responseEntity.getHeaders().getFirst("Content-Type"));
		assertEquals("text/plain", responseEntity.getHeaders().getFirst("Content-Type"));

		assertFalse(httpEntity.equals(responseEntity));
		assertFalse(responseEntity.equals(httpEntity));
		assertTrue(responseEntity.equals(responseEntity2));
		assertTrue(responseEntity2.equals(responseEntity));
	}
