	@Test
	public void handleMissingPathVariable() throws NoSuchMethodException {
		Method method = getClass().getMethod("handle", String.class);
		MethodParameter parameter = new MethodParameter(method, 0);
		MissingPathVariableException ex = new MissingPathVariableException("foo", parameter);
		ModelAndView mav = exceptionResolver.resolveException(request, response, null, ex);
		assertNotNull("No ModelAndView returned", mav);
		assertTrue("No Empty ModelAndView returned", mav.isEmpty());
		assertEquals("Invalid status code", 500, response.getStatus());
		assertEquals("Missing URI template variable 'foo' for method parameter of type String",
				response.getErrorMessage());
	}
