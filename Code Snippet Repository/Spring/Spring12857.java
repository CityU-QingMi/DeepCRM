	@Test
	public void resolveRedirectAttributesAtArgument() throws Exception {
		IllegalArgumentException ex = new IllegalArgumentException();
		HandlerMethod handlerMethod = new HandlerMethod(new RedirectAttributesController(), "handle");
		this.resolver.afterPropertiesSet();
		ModelAndView mav = this.resolver.resolveException(this.request, this.response, handlerMethod, ex);

		assertNotNull(mav);
		assertEquals("redirect:/", mav.getViewName());
		FlashMap flashMap = (FlashMap) this.request.getAttribute(DispatcherServlet.OUTPUT_FLASH_MAP_ATTRIBUTE);
		assertNotNull("output FlashMap should exist", flashMap);
		assertEquals("IllegalArgumentException", flashMap.get("exceptionClassName"));
	}
