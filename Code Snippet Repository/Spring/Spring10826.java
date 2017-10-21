	@Test
	public void resolveArgumentBindingDisabled() throws Exception {
		String name = "noBindAttr";
		Object target = new TestBean();
		this.container.addAttribute(name, target);

		StubRequestDataBinder dataBinder = new StubRequestDataBinder(target, name);
		WebDataBinderFactory factory = mock(WebDataBinderFactory.class);
		given(factory.createBinder(this.request, target, name)).willReturn(dataBinder);

		this.processor.resolveArgument(this.paramBindingDisabledAttr, this.container, this.request, factory);

		assertFalse(dataBinder.isBindInvoked());
		assertTrue(dataBinder.isValidateInvoked());
	}
