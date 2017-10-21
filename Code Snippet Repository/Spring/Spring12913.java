	@Test
	public void modelAndViewResolver() throws Exception {
		MethodParameter returnType = new MethodParameter(getClass().getDeclaredMethod("testBeanReturnValue"), -1);
		mavResolvers.add(new TestModelAndViewResolver(TestBean.class));
		TestBean testBean = new TestBean("name");

		handler.handleReturnValue(testBean, returnType, mavContainer, request);

		assertEquals("viewName", mavContainer.getViewName());
		assertSame(testBean, mavContainer.getModel().get("modelAttrName"));
		assertFalse(mavContainer.isRequestHandled());
	}
