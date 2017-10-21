	@Test
	public void emptyCacheControl() {
		Integer entity = 42;

		ResponseEntity<Integer> responseEntity =
				ResponseEntity.status(HttpStatus.OK)
						.cacheControl(CacheControl.empty())
						.body(entity);

		assertNotNull(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertFalse(responseEntity.getHeaders().containsKey(HttpHeaders.CACHE_CONTROL));
		assertEquals(entity, responseEntity.getBody());
	}
