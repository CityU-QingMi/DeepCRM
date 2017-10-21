	@Test
	public void get() {
		RequestEntity<Void> requestEntity = RequestEntity.get(URI.create("http://example.com")).accept(
				MediaType.IMAGE_GIF, MediaType.IMAGE_JPEG, MediaType.IMAGE_PNG).build();

		assertNotNull(requestEntity);
		assertEquals(HttpMethod.GET, requestEntity.getMethod());
		assertTrue(requestEntity.getHeaders().containsKey("Accept"));
		assertEquals("image/gif, image/jpeg, image/png", requestEntity.getHeaders().getFirst("Accept"));
		assertNull(requestEntity.getBody());
	}
