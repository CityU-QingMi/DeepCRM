	@Test
	public void handleRedirectAttributesWithViewReference() throws Exception {
		RedirectAttributesModelMap redirectAttributes  = new RedirectAttributesModelMap();
		mavContainer.setRedirectModel(redirectAttributes);

		ModelAndView mav = new ModelAndView(new RedirectView(), "attrName", "attrValue");
		handler.handleReturnValue(mav, returnParamModelAndView, mavContainer, webRequest);

		assertEquals(RedirectView.class, mavContainer.getView().getClass());
		assertEquals("attrValue", mavContainer.getModel().get("attrName"));
		assertSame("RedirectAttributes should be used if controller redirects", redirectAttributes,
				mavContainer.getModel());
	}
