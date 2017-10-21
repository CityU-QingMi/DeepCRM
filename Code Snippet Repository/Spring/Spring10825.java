	@Test
	public void resolveArgumentBindingDisabledPreviously() throws Exception {
		String name = "attrName";
		Object target = new TestBean();
		this.container.addAttribute(name, target);

		// Declare binding disabled (e.g. via @ModelAttribute method)
		this.container.setBindingDisabled(name);

		StubRequestDataBinder dataBinder = new StubRequestDataBinder(target, name);
		WebDataBinderFactory factory = mock(WebDataBinderFactory.class);
		given(factory.createBinder(this.request, target, name)).willReturn(dataBinder);

		this.processor.resolveArgument(this.paramNamedValidModelAttr, this.container, this.request, factory);

		assertFalse(dataBinder.isBindInvoked());
		assertTrue(dataBinder.isValidateInvoked());
	}
