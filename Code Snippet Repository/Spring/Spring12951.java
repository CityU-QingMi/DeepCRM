	@Test
	public void modelAttributePackageNameAdvice() throws Exception {
		this.webAppContext.registerSingleton("mapa", ModelAttributePackageAdvice.class);
		this.webAppContext.registerSingleton("manupa", ModelAttributeNotUsedPackageAdvice.class);
		this.webAppContext.refresh();

		HandlerMethod handlerMethod = handlerMethod(new SimpleController(), "handle");
		this.handlerAdapter.afterPropertiesSet();
		ModelAndView mav = this.handlerAdapter.handle(this.request, this.response, handlerMethod);

		assertEquals("lAttr1", mav.getModel().get("attr1"));
		assertEquals("gAttr2", mav.getModel().get("attr2"));
		assertEquals(null,mav.getModel().get("attr3"));
	}
