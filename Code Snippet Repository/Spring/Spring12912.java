	@Test
	public void handleRedirectWithIgnoreDefaultModel() throws Exception {
		mavContainer.setIgnoreDefaultModelOnRedirect(true);

		RedirectView redirectView = new RedirectView();
		ModelAndView mav = new ModelAndView(redirectView, "name", "value");
		handler.handleReturnValue(mav, returnParamModelAndView, mavContainer, webRequest);

		ModelMap model = mavContainer.getModel();
		assertSame(redirectView, mavContainer.getView());
		assertEquals(1, model.size());
		assertEquals("value", model.get("name"));
	}
