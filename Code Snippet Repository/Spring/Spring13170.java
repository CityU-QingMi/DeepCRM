	@Test
	public void invokeAndHandle_VoidWithHttpServletResponseArgument() throws Exception {
		this.argumentResolvers.addResolver(new ServletResponseMethodArgumentResolver());

		ServletInvocableHandlerMethod handlerMethod =
				getHandlerMethod(new Handler(), "httpServletResponse", HttpServletResponse.class);
		handlerMethod.invokeAndHandle(this.webRequest, this.mavContainer);

		assertTrue("Null return value + HttpServletResponse arg should result in 'request handled'",
				this.mavContainer.isRequestHandled());
	}
