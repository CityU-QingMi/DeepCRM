	@Test
	public void cacheControl() {
		Integer entity = 42;

		ResponseEntity<Integer> responseEntity =
				ResponseEntity.status(HttpStatus.OK)
						.cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).cachePrivate().
								mustRevalidate().proxyRevalidate().sMaxAge(30, TimeUnit.MINUTES))
						.body(entity);

		assertNotNull(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue(responseEntity.getHeaders().containsKey(HttpHeaders.CACHE_CONTROL));
		assertEquals(entity, responseEntity.getBody());
		String cacheControlHeader = responseEntity.getHeaders().getCacheControl();
		assertThat(cacheControlHeader, Matchers.equalTo("max-age=3600, must-revalidate, private, proxy-revalidate, s-maxage=1800"));
	}
