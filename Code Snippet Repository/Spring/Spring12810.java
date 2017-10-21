	@Test
	public void getHandlerEmptyPathMatch() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "");
		HandlerMethod handlerMethod = getHandler(request);

		assertEquals(this.emptyMethod.getMethod(), handlerMethod.getMethod());

		request = new MockHttpServletRequest("GET", "/");
		handlerMethod = getHandler(request);

		assertEquals(this.emptyMethod.getMethod(), handlerMethod.getMethod());
	}
