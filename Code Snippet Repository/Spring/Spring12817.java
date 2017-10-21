	@SuppressWarnings("")
	@Test
	public void handleMatchUriTemplateVariablesDecode() {
		RequestMappingInfo key = RequestMappingInfo.paths("/{group}/{identifier}").build();
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/group/a%2Fb");

		UrlPathHelper pathHelper = new UrlPathHelper();
		pathHelper.setUrlDecode(false);
		String lookupPath = pathHelper.getLookupPathForRequest(request);

		this.handlerMapping.setUrlPathHelper(pathHelper);
		this.handlerMapping.handleMatch(key, lookupPath, request);

		String name = HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE;
		Map<String, String> uriVariables = (Map<String, String>) request.getAttribute(name);

		assertNotNull(uriVariables);
		assertEquals("group", uriVariables.get("group"));
		assertEquals("a/b", uriVariables.get("identifier"));
	}
