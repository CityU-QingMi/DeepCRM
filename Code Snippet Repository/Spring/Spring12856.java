	@Test
	public void resolveExceptionModelAtArgument() throws Exception {
		IllegalArgumentException ex = new IllegalArgumentException();
		HandlerMethod handlerMethod = new HandlerMethod(new ModelArgumentController(), "handle");
		this.resolver.afterPropertiesSet();
		ModelAndView mav = this.resolver.resolveException(this.request, this.response, handlerMethod, ex);

		assertNotNull(mav);
		assertEquals(1, mav.getModelMap().size());
		assertEquals("IllegalArgumentException", mav.getModelMap().get("exceptionClassName"));
	}
