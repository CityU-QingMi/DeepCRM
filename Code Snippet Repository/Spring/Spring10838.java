	@Test
	public void updateModelSessionAttributesRemoved() throws Exception {
		String attributeName = "sessionAttr";
		String attribute = "value";
		ModelAndViewContainer container = new ModelAndViewContainer();
		container.addAttribute(attributeName, attribute);

		this.attributeStore.storeAttribute(this.webRequest, attributeName, attribute);

		WebDataBinder dataBinder = new WebDataBinder(attribute, attributeName);
		WebDataBinderFactory binderFactory = mock(WebDataBinderFactory.class);
		given(binderFactory.createBinder(this.webRequest, attribute, attributeName)).willReturn(dataBinder);

		container.getSessionStatus().setComplete();

		ModelFactory modelFactory = new ModelFactory(null, binderFactory, this.attributeHandler);
		modelFactory.updateModel(this.webRequest, container);

		assertEquals(attribute, container.getModel().get(attributeName));
		assertNull(this.attributeStore.retrieveAttribute(this.webRequest, attributeName));
	}
