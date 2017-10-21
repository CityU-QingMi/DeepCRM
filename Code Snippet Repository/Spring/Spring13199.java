	@Test
	public void streamingResponseBody() throws Exception {
		CountDownLatch latch = new CountDownLatch(1);

		MethodParameter returnType = returnType(TestController.class, "handle");
		StreamingResponseBody streamingBody = outputStream -> {
			outputStream.write("foo".getBytes(StandardCharsets.UTF_8));
			latch.countDown();
		};
		this.handler.handleReturnValue(streamingBody, returnType, this.mavContainer, this.webRequest);

		assertTrue(this.request.isAsyncStarted());
		assertTrue(latch.await(5, TimeUnit.SECONDS));
		assertEquals("foo", this.response.getContentAsString());
	}
