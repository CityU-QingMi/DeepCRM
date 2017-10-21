	@Test
	public void setAlwaysUseRedirectAttributes() throws Exception {
		HandlerMethodArgumentResolver redirectAttributesResolver = new RedirectAttributesMethodArgumentResolver();
		HandlerMethodArgumentResolver modelResolver = new ModelMethodProcessor();
		HandlerMethodReturnValueHandler viewHandler = new ViewNameMethodReturnValueHandler();

		this.handlerAdapter.setArgumentResolvers(Arrays.asList(redirectAttributesResolver, modelResolver));
		this.handlerAdapter.setReturnValueHandlers(Collections.singletonList(viewHandler));
		this.handlerAdapter.setIgnoreDefaultModelOnRedirect(true);
		this.handlerAdapter.afterPropertiesSet();

		this.request.setAttribute(DispatcherServlet.OUTPUT_FLASH_MAP_ATTRIBUTE, new FlashMap());

		HandlerMethod handlerMethod = handlerMethod(new RedirectAttributeController(), "handle", Model.class);
		ModelAndView mav = this.handlerAdapter.handle(request, response, handlerMethod);

		assertTrue("Without RedirectAttributes arg, model should be empty", mav.getModel().isEmpty());
	}
