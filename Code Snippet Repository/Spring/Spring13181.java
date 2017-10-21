	@Test
	public void createAttributeUriTemplateVar() throws Exception {
		Map<String, String> uriTemplateVars = new HashMap<>();
		uriTemplateVars.put("testBean1", "Patty");
		request.setAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, uriTemplateVars);

		// Type conversion from "Patty" to TestBean via TestBean(String) constructor
		TestBean testBean = (TestBean) processor.resolveArgument(
				testBeanModelAttr, mavContainer, webRequest, binderFactory);

		assertEquals("Patty", testBean.getName());
	}
