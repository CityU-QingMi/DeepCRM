	@Test
	public void createAttributeUriTemplateVarWithOptional() throws Exception {
		Map<String, String> uriTemplateVars = new HashMap<>();
		uriTemplateVars.put("testBean3", "Patty");
		request.setAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, uriTemplateVars);

		// Type conversion from "Patty" to TestBean via TestBean(String) constructor
		Optional<TestBean> testBean = (Optional<TestBean>) processor.resolveArgument(
				testBeanWithOptionalModelAttr, mavContainer, webRequest, binderFactory);

		assertEquals("Patty", testBean.get().getName());
	}
