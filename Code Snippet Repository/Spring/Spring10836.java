	@Test
	public void updateModelBindingResult() throws Exception {
		String commandName = "attr1";
		Object command = new Object();
		ModelAndViewContainer container = new ModelAndViewContainer();
		container.addAttribute(commandName, command);

		WebDataBinder dataBinder = new WebDataBinder(command, commandName);
		WebDataBinderFactory binderFactory = mock(WebDataBinderFactory.class);
		given(binderFactory.createBinder(this.webRequest, command, commandName)).willReturn(dataBinder);

		ModelFactory modelFactory = new ModelFactory(null, binderFactory, this.attributeHandler);
		modelFactory.updateModel(this.webRequest, container);

		assertEquals(command, container.getModel().get(commandName));
		String bindingResultKey = BindingResult.MODEL_KEY_PREFIX + commandName;
		assertSame(dataBinder.getBindingResult(), container.getModel().get(bindingResultKey));
		assertEquals(2, container.getModel().size());
	}
