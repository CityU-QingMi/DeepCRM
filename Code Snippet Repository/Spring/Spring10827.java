	@Test(expected = BindException.class)
	public void resolveArgumentBindException() throws Exception {
		String name = "testBean";
		Object target = new TestBean();
		this.container.getModel().addAttribute(target);

		StubRequestDataBinder dataBinder = new StubRequestDataBinder(target, name);
		dataBinder.getBindingResult().reject("error");
		WebDataBinderFactory binderFactory = mock(WebDataBinderFactory.class);
		given(binderFactory.createBinder(this.request, target, name)).willReturn(dataBinder);

		this.processor.resolveArgument(this.paramNonSimpleType, this.container, this.request, binderFactory);
		verify(binderFactory).createBinder(this.request, target, name);
	}
