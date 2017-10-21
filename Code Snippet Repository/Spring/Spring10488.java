	@Test
	public void getAndInterceptError() throws Exception {
		RequestInterceptor interceptor = new RequestInterceptor();
		template.setInterceptors(Collections.singletonList(interceptor));
		template.getForEntity(baseUrl + "/status/notfound", String.class);

		interceptor.latch.await(5, TimeUnit.SECONDS);
		assertNotNull(interceptor.response);
		assertEquals(HttpStatus.NOT_FOUND, interceptor.response.getStatusCode());
		assertNull(interceptor.exception);
	}
