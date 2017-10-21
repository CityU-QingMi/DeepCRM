	@SuppressWarnings("")
	@Test
	public void handleMatchUriTemplateVariables() {
		RequestMappingInfo key = RequestMappingInfo.paths("/{path1}/{path2}").build();
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/1/2");
		String lookupPath = new UrlPathHelper().getLookupPathForRequest(request);
		this.handlerMapping.handleMatch(key, lookupPath, request);

		String name = HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE;
		Map<String, String> uriVariables = (Map<String, String>) request.getAttribute(name);

		assertNotNull(uriVariables);
		assertEquals("1", uriVariables.get("path1"));
		assertEquals("2", uriVariables.get("path2"));
	}
