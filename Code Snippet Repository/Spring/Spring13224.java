	@Test
	public void redirectStatus() throws Exception {
		this.controller.setStatusCode(HttpStatus.PERMANENT_REDIRECT);
		this.controller.setViewName("/foo");
		ModelAndView modelAndView = this.controller.handleRequest(this.request, this.response);

		assertEquals("redirect:/foo", modelAndView.getViewName());
		assertEquals("3xx status should be left to RedirectView to set", 200, this.response.getStatus());
		assertEquals(HttpStatus.PERMANENT_REDIRECT, this.request.getAttribute(View.RESPONSE_STATUS_ATTRIBUTE));
	}
