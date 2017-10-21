	@Test
	public void updateModelSessionAttributesSaved() throws Exception {
		String attributeName = "sessionAttr";
		String attribute = "value";
		ModelAndViewContainer container = new ModelAndViewContainer();
		container.addAttribute(attributeName, attribute);

		WebDataBinder dataBinder = new WebDataBinder(attribute, attributeName);
		WebDataBinderFactory binderFactory = mock(WebDataBinderFactory.class);
		given(binderFactory.createBinder(this.webRequest, attribute, attributeName)).willReturn(dataBinder);

		ModelFactory modelFactory = new ModelFactory(null, binderFactory, this.attributeHandler);
		modelFactory.updateModel(this.webRequest, container);

		assertEquals(attribute, container.getModel().get(attributeName));
		assertEquals(attribute, this.attributeStore.retrieveAttribute(this.webRequest, attributeName));
	}
