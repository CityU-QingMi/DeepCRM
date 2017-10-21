	@Test
	public void resolveExceptionControllerAdviceNoHandler() throws Exception {
		AnnotationConfigApplicationContext cxt = new AnnotationConfigApplicationContext(MyControllerAdviceConfig.class);
		this.resolver.setApplicationContext(cxt);
		this.resolver.afterPropertiesSet();

		IllegalStateException ex = new IllegalStateException();
		ModelAndView mav = this.resolver.resolveException(this.request, this.response, null, ex);

		assertNotNull("Exception was not handled", mav);
		assertTrue(mav.isEmpty());
		assertEquals("DefaultTestExceptionResolver: IllegalStateException", this.response.getContentAsString());
	}
