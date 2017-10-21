	@Test
	public void invocationErrorMessage() throws Exception {
		HandlerMethodArgumentResolverComposite composite = new HandlerMethodArgumentResolverComposite();
		composite.addResolver(new StubArgumentResolver(double.class, null));

		Method method = Handler.class.getDeclaredMethod("handle", double.class);
		Object handler = new Handler();
		InvocableHandlerMethod hm = new InvocableHandlerMethod(handler, method);
		hm.setHandlerMethodArgumentResolvers(composite);

		try {
			hm.invokeForRequest(this.webRequest, new ModelAndViewContainer());
			fail();
		}
		catch (IllegalStateException ex) {
			assertThat(ex.getMessage(), containsString("Illegal argument"));
		}
	}
