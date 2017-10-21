	@Test
	public void responseBodyEmitter() throws Exception {
		MethodParameter type = on(TestController.class).resolveReturnType(ResponseBodyEmitter.class);
		ResponseBodyEmitter emitter = new ResponseBodyEmitter();
		this.handler.handleReturnValue(emitter, type, this.mavContainer, this.webRequest);

		assertTrue(this.request.isAsyncStarted());
		assertEquals("", this.response.getContentAsString());

		SimpleBean bean = new SimpleBean();
		bean.setId(1L);
		bean.setName("Joe");
		emitter.send(bean);
		emitter.send("\n");

		bean.setId(2L);
		bean.setName("John");
		emitter.send(bean);
		emitter.send("\n");

		bean.setId(3L);
		bean.setName("Jason");
		emitter.send(bean);

		assertEquals("{\"id\":1,\"name\":\"Joe\"}\n" +
						"{\"id\":2,\"name\":\"John\"}\n" +
						"{\"id\":3,\"name\":\"Jason\"}", this.response.getContentAsString());

		MockAsyncContext asyncContext = (MockAsyncContext) this.request.getAsyncContext();
		assertNull(asyncContext.getDispatchedPath());

		emitter.complete();
		assertNotNull(asyncContext.getDispatchedPath());
	}
