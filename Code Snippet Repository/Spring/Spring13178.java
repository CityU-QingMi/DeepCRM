	@Test
	public void wrapConcurrentResult_StreamingResponseBody() throws Exception {
		this.returnValueHandlers.addHandler(new StreamingResponseBodyReturnValueHandler());
		ServletInvocableHandlerMethod handlerMethod = getHandlerMethod(new StreamingHandler(), "handleStreamBody");
		handlerMethod = handlerMethod.wrapConcurrentResult(null);
		handlerMethod.invokeAndHandle(this.webRequest, this.mavContainer);

		assertEquals(200, this.response.getStatus());
		assertEquals("", this.response.getContentAsString());
	}
