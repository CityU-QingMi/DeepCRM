	@Test
	public void modelAndView() throws Exception {
		BindException bindException = new BindException(new Object(), "target");
		bindException.reject("errorCode");

		ModelAndView mav = new ModelAndView("viewName");
		mav.addObject("attrName", "attrValue");
		mav.addObject(BindingResult.MODEL_KEY_PREFIX + "attrName", bindException);

		this.mvcResult.setMav(mav);
		this.handler.handle(this.mvcResult);

		assertValue("ModelAndView", "View name", "viewName");
		assertValue("ModelAndView", "View", null);
		assertValue("ModelAndView", "Attribute", "attrName");
		assertValue("ModelAndView", "value", "attrValue");
		assertValue("ModelAndView", "errors", bindException.getAllErrors());
	}
