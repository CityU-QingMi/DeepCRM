	@Test
	public void modelAttributeAdviceInParentContext() throws Exception {
		StaticWebApplicationContext parent = new StaticWebApplicationContext();
		parent.registerSingleton("maa", ModelAttributeAdvice.class);
		parent.refresh();
		this.webAppContext.setParent(parent);
		this.webAppContext.refresh();

		HandlerMethod handlerMethod = handlerMethod(new SimpleController(), "handle");
		this.handlerAdapter.afterPropertiesSet();
		ModelAndView mav = this.handlerAdapter.handle(this.request, this.response, handlerMethod);

		assertEquals("lAttr1", mav.getModel().get("attr1"));
		assertEquals("gAttr2", mav.getModel().get("attr2"));
	}
