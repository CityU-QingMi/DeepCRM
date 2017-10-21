	@Test
	public void invokeAndHandle_DynamicReturnValue() throws Exception {
		this.argumentResolvers.addResolver(new RequestParamMethodArgumentResolver(null, false));
		this.returnValueHandlers.addHandler(new ViewMethodReturnValueHandler());
		this.returnValueHandlers.addHandler(new ViewNameMethodReturnValueHandler());

		// Invoke without a request parameter (String return value)
		ServletInvocableHandlerMethod handlerMethod = getHandlerMethod(new Handler(), "dynamicReturnValue", String.class);
		handlerMethod.invokeAndHandle(this.webRequest, this.mavContainer);

		assertNotNull(this.mavContainer.getView());
		assertEquals(RedirectView.class, this.mavContainer.getView().getClass());

		// Invoke with a request parameter (RedirectView return value)
		this.request.setParameter("param", "value");
		handlerMethod.invokeAndHandle(this.webRequest, this.mavContainer);

		assertEquals("view", this.mavContainer.getViewName());
	}
