	@Test
	public void cacheControlNoCache() {
		Integer entity = 42;

		ResponseEntity<Integer> responseEntity =
				ResponseEntity.status(HttpStatus.OK)
						.cacheControl(CacheControl.noStore())
						.body(entity);

		assertNotNull(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue(responseEntity.getHeaders().containsKey(HttpHeaders.CACHE_CONTROL));
		assertEquals(entity, responseEntity.getBody());

		String cacheControlHeader = responseEntity.getHeaders().getCacheControl();
		assertThat(cacheControlHeader, Matchers.equalTo("no-store"));
	}
