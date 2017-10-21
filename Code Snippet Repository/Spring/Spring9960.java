	@Test
	public void requestEntity() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		String body = "foo";
		HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);
		RequestEntity<String> requestEntity = new RequestEntity<>(body, headers, HttpMethod.GET, new URI("/"));
		RequestEntity<String> requestEntity2 = new RequestEntity<>(body, headers, HttpMethod.GET, new URI("/"));

		assertEquals(body, requestEntity.getBody());
		assertEquals(MediaType.TEXT_PLAIN, requestEntity.getHeaders().getContentType());
		assertEquals("text/plain", requestEntity.getHeaders().getFirst("Content-Type"));
		assertEquals("text/plain", requestEntity.getHeaders().getFirst("Content-Type"));

		assertFalse(httpEntity.equals(requestEntity));
		assertFalse(requestEntity.equals(httpEntity));
		assertTrue(requestEntity.equals(requestEntity2));
		assertTrue(requestEntity2.equals(requestEntity));
	}
