	@Test
	public void returnViewRedirect() throws Exception {
		RedirectView redirectView = new RedirectView("testView");
		ModelMap redirectModel = new RedirectAttributesModelMap();
		this.mavContainer.setRedirectModel(redirectModel);
		MethodParameter param = createReturnValueParam("view");
		this.handler.handleReturnValue(redirectView, param, this.mavContainer, this.webRequest);

		assertSame(redirectView, this.mavContainer.getView());
		assertSame("Should have switched to the RedirectModel", redirectModel, this.mavContainer.getModel());
	}
