	@Test
	public void resolveArgument() throws Exception {
		Map<String, String> uriTemplateVars = new HashMap<>();
		uriTemplateVars.put("name", "value");
		request.setAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, uriTemplateVars);

		String result = (String) resolver.resolveArgument(paramNamedString, mavContainer, webRequest, null);
		assertEquals("PathVariable not resolved correctly", "value", result);

		@SuppressWarnings("unchecked")
		Map<String, Object> pathVars = (Map<String, Object>) request.getAttribute(View.PATH_VARIABLES);
		assertNotNull(pathVars);
		assertEquals(1, pathVars.size());
		assertEquals("value", pathVars.get("name"));
	}
