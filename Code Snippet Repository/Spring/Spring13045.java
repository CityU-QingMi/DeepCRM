	@Test
	public void responseEntitySseNoContent() throws Exception {
		MethodParameter type = on(TestController.class).resolveReturnType(ResponseEntity.class, SseEmitter.class);
		ResponseEntity<?> entity = ResponseEntity.noContent().header("foo", "bar").build();
		this.handler.handleReturnValue(entity, type, this.mavContainer, this.webRequest);

		assertFalse(this.request.isAsyncStarted());
		assertEquals(204, this.response.getStatus());
		assertEquals(Collections.singletonList("bar"), this.response.getHeaders("foo"));
	}
