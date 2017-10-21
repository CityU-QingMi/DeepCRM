	@Test
	public void updateModelWhenRedirecting() throws Exception {
		String attributeName = "sessionAttr";
		String attribute = "value";
		ModelAndViewContainer container = new ModelAndViewContainer();
		container.addAttribute(attributeName, attribute);

		String queryParam = "123";
		String queryParamName = "q";
		container.setRedirectModel(new ModelMap(queryParamName, queryParam));
		container.setRedirectModelScenario(true);

		WebDataBinder dataBinder = new WebDataBinder(attribute, attributeName);
		WebDataBinderFactory binderFactory = mock(WebDataBinderFactory.class);
		given(binderFactory.createBinder(this.webRequest, attribute, attributeName)).willReturn(dataBinder);

		ModelFactory modelFactory = new ModelFactory(null, binderFactory, this.attributeHandler);
		modelFactory.updateModel(this.webRequest, container);

		assertEquals(queryParam, container.getModel().get(queryParamName));
		assertEquals(1, container.getModel().size());
		assertEquals(attribute, this.attributeStore.retrieveAttribute(this.webRequest, attributeName));
	}
