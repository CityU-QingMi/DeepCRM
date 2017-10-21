	@Test
	public void resolveArgumentValidation() throws Exception {
		String name = "attrName";
		Object target = new TestBean();
		this.container.addAttribute(name, target);

		StubRequestDataBinder dataBinder = new StubRequestDataBinder(target, name);
		WebDataBinderFactory factory = mock(WebDataBinderFactory.class);
		given(factory.createBinder(this.request, target, name)).willReturn(dataBinder);

		this.processor.resolveArgument(this.paramNamedValidModelAttr, this.container, this.request, factory);

		assertTrue(dataBinder.isBindInvoked());
		assertTrue(dataBinder.isValidateInvoked());
	}
