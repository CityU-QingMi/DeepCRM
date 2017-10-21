	@Test
	public void controllerAdvice() throws Exception {
		StaticWebApplicationContext cxt = new StaticWebApplicationContext();
		cxt.registerSingleton("exceptionHandler", ApplicationExceptionHandler.class);
		cxt.refresh();

		ExceptionHandlerExceptionResolver resolver = new ExceptionHandlerExceptionResolver();
		resolver.setApplicationContext(cxt);
		resolver.afterPropertiesSet();

		ServletRequestBindingException ex = new ServletRequestBindingException("message");
		resolver.resolveException(this.servletRequest, this.servletResponse, null, ex);

		assertEquals(400, this.servletResponse.getStatus());
		assertEquals("error content", this.servletResponse.getContentAsString());
		assertEquals("someHeaderValue", this.servletResponse.getHeader("someHeader"));
	}
