	@Test
	public void handleMissingServletRequestPartException() throws Exception {
		MissingServletRequestPartException ex = new MissingServletRequestPartException("name");
		ModelAndView mav = exceptionResolver.resolveException(request, response, null, ex);
		assertNotNull("No ModelAndView returned", mav);
		assertTrue("No Empty ModelAndView returned", mav.isEmpty());
		assertEquals("Invalid status code", 400, response.getStatus());
		assertTrue(response.getErrorMessage().contains("request part"));
		assertTrue(response.getErrorMessage().contains("name"));
		assertTrue(response.getErrorMessage().contains("not present"));
	}
