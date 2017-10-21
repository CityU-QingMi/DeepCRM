	@Test
	public void createAttributeUriTemplateVarCannotConvert() throws Exception {
		Map<String, String> uriTemplateVars = new HashMap<>();
		uriTemplateVars.put("testBean2", "Patty");
		request.setAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, uriTemplateVars);

		TestBeanWithoutStringConstructor testBean = (TestBeanWithoutStringConstructor) processor.resolveArgument(
				testBeanWithoutStringConstructorModelAttr, mavContainer, webRequest, binderFactory);

		assertNotNull(testBean);
	}
