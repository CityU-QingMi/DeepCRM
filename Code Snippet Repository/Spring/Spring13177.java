	@Test
	public void wrapConcurrentResult_ResponseBodyEmitter() throws Exception {

		this.returnValueHandlers.addHandler(new ResponseBodyEmitterReturnValueHandler(this.converters));

		ServletInvocableHandlerMethod handlerMethod = getHandlerMethod(new StreamingHandler(), "handleEmitter");
		handlerMethod = handlerMethod.wrapConcurrentResult(null);
		handlerMethod.invokeAndHandle(this.webRequest, this.mavContainer);

		assertEquals(200, this.response.getStatus());
		assertEquals("", this.response.getContentAsString());
	}
