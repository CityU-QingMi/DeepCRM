	@Test
	public void handleRequestBody() throws Exception {
		Class<?>[] parameterTypes = new Class<?>[] {byte[].class};

		request.setMethod("POST");
		request.addHeader("Content-Type", "text/plain; charset=utf-8");
		request.setContent("Hello Server".getBytes("UTF-8"));

		HandlerMethod handlerMethod = handlerMethod("handleRequestBody", parameterTypes);

		ModelAndView mav = handlerAdapter.handle(request, response, handlerMethod);

		assertNull(mav);
		assertEquals("Handled requestBody=[Hello Server]", new String(response.getContentAsByteArray(), "UTF-8"));
		assertEquals(HttpStatus.ACCEPTED.value(), response.getStatus());
	}
