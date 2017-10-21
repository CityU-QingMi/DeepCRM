	@Test
	public void responseEntitySse() throws Exception {
		MethodParameter type = on(TestController.class).resolveReturnType(ResponseEntity.class, SseEmitter.class);
		ResponseEntity<SseEmitter> entity = ResponseEntity.ok().header("foo", "bar").body(new SseEmitter());
		this.handler.handleReturnValue(entity, type, this.mavContainer, this.webRequest);

		assertTrue(this.request.isAsyncStarted());
		assertEquals(200, this.response.getStatus());
		assertEquals("text/event-stream;charset=UTF-8", this.response.getContentType());
		assertEquals("bar", this.response.getHeader("foo"));
	}
