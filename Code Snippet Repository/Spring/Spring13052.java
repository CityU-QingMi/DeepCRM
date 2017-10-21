	@Test
	public void sseEmitter() throws Exception {
		MethodParameter type = on(TestController.class).resolveReturnType(SseEmitter.class);
		SseEmitter emitter = new SseEmitter();
		this.handler.handleReturnValue(emitter, type, this.mavContainer, this.webRequest);

		assertTrue(this.request.isAsyncStarted());
		assertEquals(200, this.response.getStatus());
		assertEquals("text/event-stream;charset=UTF-8", this.response.getContentType());

		SimpleBean bean1 = new SimpleBean();
		bean1.setId(1L);
		bean1.setName("Joe");

		SimpleBean bean2 = new SimpleBean();
		bean2.setId(2L);
		bean2.setName("John");

		emitter.send(SseEmitter.event().
				comment("a test").name("update").id("1").reconnectTime(5000L).data(bean1).data(bean2));

		assertEquals(":a test\n" +
						"event:update\n" +
						"id:1\n" +
						"retry:5000\n" +
						"data:{\"id\":1,\"name\":\"Joe\"}\n" +
						"data:{\"id\":2,\"name\":\"John\"}\n" +
						"\n", this.response.getContentAsString());
	}
