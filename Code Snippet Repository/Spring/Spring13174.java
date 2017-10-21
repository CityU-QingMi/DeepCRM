	private void wrapConcurrentResult_ResponseBody(Object handler, Object result, Class<?> expectedReturnType)
			throws Exception {

		this.returnValueHandlers.addHandler(new ModelAndViewMethodReturnValueHandler());
		this.returnValueHandlers.addHandler(new RequestResponseBodyMethodProcessor(this.converters));
		ServletInvocableHandlerMethod handlerMethod = getHandlerMethod(handler, "handle");

		handlerMethod = handlerMethod.wrapConcurrentResult(result);
		handlerMethod.invokeAndHandle(this.webRequest, this.mavContainer);
		assertEquals((result != null ? result.toString() : ""), this.response.getContentAsString());
		assertEquals(expectedReturnType, handlerMethod.getReturnValueType(result).getParameterType());
	}
