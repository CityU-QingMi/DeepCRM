	@Test
	public void handleRedirectAttributesWithoutRedirect() throws Exception {
		RedirectAttributesModelMap redirectAttributes  = new RedirectAttributesModelMap();
		mavContainer.setRedirectModel(redirectAttributes);

		ModelAndView mav = new ModelAndView();
		handler.handleReturnValue(mav, returnParamModelAndView, mavContainer, webRequest);

		ModelMap model = mavContainer.getModel();
		assertEquals(null, mavContainer.getView());
		assertTrue(mavContainer.getModel().isEmpty());
		assertNotSame("RedirectAttributes should not be used if controller doesn't redirect", redirectAttributes, model);
	}
