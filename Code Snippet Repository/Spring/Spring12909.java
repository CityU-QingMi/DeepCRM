	@Test
	public void handleRedirectAttributesWithViewName() throws Exception {
		RedirectAttributesModelMap redirectAttributes  = new RedirectAttributesModelMap();
		mavContainer.setRedirectModel(redirectAttributes);

		ModelAndView mav = new ModelAndView("redirect:viewName", "attrName", "attrValue");
		handler.handleReturnValue(mav, returnParamModelAndView, mavContainer, webRequest);

		ModelMap model = mavContainer.getModel();
		assertEquals("redirect:viewName", mavContainer.getViewName());
		assertEquals("attrValue", model.get("attrName"));
		assertSame(redirectAttributes, model);
	}
