	@Test
	public void responseEntity() throws Exception {
		CountDownLatch latch = new CountDownLatch(1);

		MethodParameter returnType = returnType(TestController.class, "handleResponseEntity");
		ResponseEntity<StreamingResponseBody> emitter = ResponseEntity.ok().header("foo", "bar")
				.body(outputStream -> {
					outputStream.write("foo".getBytes(StandardCharsets.UTF_8));
					latch.countDown();
				});
		this.handler.handleReturnValue(emitter, returnType, this.mavContainer, this.webRequest);

		assertTrue(this.request.isAsyncStarted());
		assertEquals(200, this.response.getStatus());
		assertEquals("bar", this.response.getHeader("foo"));

		assertTrue(latch.await(5, TimeUnit.SECONDS));
		assertEquals("foo", this.response.getContentAsString());

	}
