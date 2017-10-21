	@Test
	public void handleHttpEntityWithCacheControl() throws Exception {
		Class<?>[] parameterTypes = new Class<?>[] {HttpEntity.class};
		request.addHeader("Content-Type", "text/plain; charset=utf-8");
		request.setContent("Hello Server".getBytes("UTF-8"));

		HandlerMethod handlerMethod = handlerMethod("handleHttpEntityWithCacheControl", parameterTypes);
		ModelAndView mav = handlerAdapter.handle(request, response, handlerMethod);

		assertNull(mav);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals("Handled requestBody=[Hello Server]", new String(response.getContentAsByteArray(), "UTF-8"));
		assertThat(response.getHeaderValues("Cache-Control"), Matchers.contains("max-age=3600"));
	}
