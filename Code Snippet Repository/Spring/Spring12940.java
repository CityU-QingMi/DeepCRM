	@Test
	public void handleAndValidateRequestBody() throws Exception {
		Class<?>[] parameterTypes = new Class<?>[] {TestBean.class, Errors.class};

		request.addHeader("Content-Type", "text/plain; charset=utf-8");
		request.setContent("Hello Server".getBytes("UTF-8"));

		HandlerMethod handlerMethod = handlerMethod("handleAndValidateRequestBody", parameterTypes);

		ModelAndView mav = handlerAdapter.handle(request, response, handlerMethod);

		assertNull(mav);
		assertEquals("Error count [1]", new String(response.getContentAsByteArray(), "UTF-8"));
		assertEquals(HttpStatus.ACCEPTED.value(), response.getStatus());
	}
