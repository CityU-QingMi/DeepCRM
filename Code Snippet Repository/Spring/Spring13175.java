	@Test
	public void wrapConcurrentResult_ResponseEntityNullBody() throws Exception {
		this.returnValueHandlers.addHandler(new HttpEntityMethodProcessor(this.converters));
		ServletInvocableHandlerMethod handlerMethod = getHandlerMethod(new ResponseEntityHandler(), "handleDeferred");
		handlerMethod = handlerMethod.wrapConcurrentResult(new ResponseEntity<>(HttpStatus.OK));
		handlerMethod.invokeAndHandle(this.webRequest, this.mavContainer);

		assertEquals(200, this.response.getStatus());
		assertEquals("", this.response.getContentAsString());
	}
