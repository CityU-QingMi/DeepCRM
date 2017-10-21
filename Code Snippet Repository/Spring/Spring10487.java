	@Test
	public void getAndInterceptResponse() throws Exception {
		RequestInterceptor interceptor = new RequestInterceptor();
		template.setInterceptors(Collections.singletonList(interceptor));
		ListenableFuture<ResponseEntity<String>> future = template.getForEntity(baseUrl + "/get", String.class);

		interceptor.latch.await(5, TimeUnit.SECONDS);
		assertNotNull(interceptor.response);
		assertEquals(HttpStatus.OK, interceptor.response.getStatusCode());
		assertNull(interceptor.exception);
		assertEquals(helloWorld, future.get().getBody());
	}
