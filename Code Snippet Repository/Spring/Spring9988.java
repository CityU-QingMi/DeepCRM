	@Test
	public void methods() throws URISyntaxException {
		URI url = new URI("http://example.com");

		RequestEntity<?> entity = RequestEntity.get(url).build();
		assertEquals(HttpMethod.GET, entity.getMethod());

		entity = RequestEntity.post(url).build();
		assertEquals(HttpMethod.POST, entity.getMethod());

		entity = RequestEntity.head(url).build();
		assertEquals(HttpMethod.HEAD, entity.getMethod());

		entity = RequestEntity.options(url).build();
		assertEquals(HttpMethod.OPTIONS, entity.getMethod());

		entity = RequestEntity.put(url).build();
		assertEquals(HttpMethod.PUT, entity.getMethod());

		entity = RequestEntity.patch(url).build();
		assertEquals(HttpMethod.PATCH, entity.getMethod());

		entity = RequestEntity.delete(url).build();
		assertEquals(HttpMethod.DELETE, entity.getMethod());

	}
