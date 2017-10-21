	private void testHttpOptions(String requestURI, String allowHeader) throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest("OPTIONS", requestURI);
		HandlerMethod handlerMethod = getHandler(request);

		ServletWebRequest webRequest = new ServletWebRequest(request);
		ModelAndViewContainer mavContainer = new ModelAndViewContainer();
		Object result = new InvocableHandlerMethod(handlerMethod).invokeForRequest(webRequest, mavContainer);

		assertNotNull(result);
		assertEquals(HttpHeaders.class, result.getClass());
		assertEquals(allowHeader, ((HttpHeaders) result).getFirst("Allow"));
	}
