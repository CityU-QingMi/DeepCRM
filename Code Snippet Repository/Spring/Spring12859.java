	@Test
	public void resolveExceptionGlobalHandlerOrdered() throws Exception {
		AnnotationConfigApplicationContext cxt = new AnnotationConfigApplicationContext(MyConfig.class);
		this.resolver.setApplicationContext(cxt);
		this.resolver.afterPropertiesSet();

		IllegalStateException ex = new IllegalStateException();
		HandlerMethod handlerMethod = new HandlerMethod(new ResponseBodyController(), "handle");
		ModelAndView mav = this.resolver.resolveException(this.request, this.response, handlerMethod, ex);

		assertNotNull("Exception was not handled", mav);
		assertTrue(mav.isEmpty());
		assertEquals("TestExceptionResolver: IllegalStateException", this.response.getContentAsString());
	}
