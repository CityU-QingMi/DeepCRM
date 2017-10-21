	@Test
	public void resolveArgumentWithExistingPathVars() throws Exception {
		Map<String, String> uriTemplateVars = new HashMap<>();
		uriTemplateVars.put("name", "value");
		request.setAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, uriTemplateVars);

		uriTemplateVars.put("oldName", "oldValue");
		request.setAttribute(View.PATH_VARIABLES, uriTemplateVars);

		String result = (String) resolver.resolveArgument(paramNamedString, mavContainer, webRequest, null);
		assertEquals("PathVariable not resolved correctly", "value", result);

		@SuppressWarnings("unchecked")
		Map<String, Object> pathVars = (Map<String, Object>) request.getAttribute(View.PATH_VARIABLES);
		assertNotNull(pathVars);
		assertEquals(2, pathVars.size());
		assertEquals("value", pathVars.get("name"));
		assertEquals("oldValue", pathVars.get("oldName"));
	}
