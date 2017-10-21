	@Test
	public void handleConversionNotSupportedException() throws Exception {
		ConversionNotSupportedException ex =
				new ConversionNotSupportedException(new Object(), String.class, new Exception());
		ModelAndView mav = exceptionResolver.resolveException(request, response, null, ex);
		assertNotNull("No ModelAndView returned", mav);
		assertTrue("No Empty ModelAndView returned", mav.isEmpty());
		assertEquals("Invalid status code", 500, response.getStatus());

		// SPR-9653
		assertSame(ex, request.getAttribute("javax.servlet.error.exception"));
	}
