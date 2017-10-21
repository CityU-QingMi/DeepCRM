	private void testGetAttributeFromModel(String expectedAttrName, MethodParameter param) throws Exception {
		Object target = new TestBean();
		this.container.addAttribute(expectedAttrName, target);

		WebDataBinder dataBinder = new WebRequestDataBinder(target);
		WebDataBinderFactory factory = mock(WebDataBinderFactory.class);
		given(factory.createBinder(this.request, target, expectedAttrName)).willReturn(dataBinder);

		this.processor.resolveArgument(param, this.container, this.request, factory);
		verify(factory).createBinder(this.request, target, expectedAttrName);
	}
