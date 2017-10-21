	@Test
	public void resolveExceptionWithAssertionErrorAsRootCause() throws Exception {
		AnnotationConfigApplicationContext cxt = new AnnotationConfigApplicationContext(MyConfig.class);
		this.resolver.setApplicationContext(cxt);
		this.resolver.afterPropertiesSet();

		AssertionError err = new AssertionError("argh");
		FatalBeanException ex = new FatalBeanException("wrapped", err);
		HandlerMethod handlerMethod = new HandlerMethod(new ResponseBodyController(), "handle");
		ModelAndView mav = this.resolver.resolveException(this.request, this.response, handlerMethod, ex);

		assertNotNull("Exception was not handled", mav);
		assertTrue(mav.isEmpty());
		assertEquals(err.toString(), this.response.getContentAsString());
	}
