	@Test
	public void handleHttpEntity() throws Exception {
		Class<?>[] parameterTypes = new Class<?>[] {HttpEntity.class};

		request.addHeader("Content-Type", "text/plain; charset=utf-8");
		request.setContent("Hello Server".getBytes("UTF-8"));

		HandlerMethod handlerMethod = handlerMethod("handleHttpEntity", parameterTypes);

		ModelAndView mav = handlerAdapter.handle(request, response, handlerMethod);

		assertNull(mav);
		assertEquals(HttpStatus.ACCEPTED.value(), response.getStatus());
		assertEquals("Handled requestBody=[Hello Server]", new String(response.getContentAsByteArray(), "UTF-8"));
		assertEquals("headerValue", response.getHeader("header"));
		// set because of @SesstionAttributes
		assertEquals("no-store", response.getHeader("Cache-Control"));
	}
