	@Test
	public void resolveArgumentOrdering() throws Exception {
		String name = "testBean";
		Object testBean = new TestBean(name);
		this.container.addAttribute(name, testBean);
		this.container.addAttribute(BindingResult.MODEL_KEY_PREFIX + name, testBean);

		Object anotherTestBean = new TestBean();
		this.container.addAttribute("anotherTestBean", anotherTestBean);

		StubRequestDataBinder dataBinder = new StubRequestDataBinder(testBean, name);
		WebDataBinderFactory binderFactory = mock(WebDataBinderFactory.class);
		given(binderFactory.createBinder(this.request, testBean, name)).willReturn(dataBinder);

		this.processor.resolveArgument(this.paramModelAttr, this.container, this.request, binderFactory);

		Object[] values = this.container.getModel().values().toArray();
		assertSame("Resolved attribute should be updated to be last", testBean, values[1]);
		assertSame("BindingResult of resolved attr should be last", dataBinder.getBindingResult(), values[2]);
	}
