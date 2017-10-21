	@Test
	public void handleMatchMatrixVariablesDecoding() {

		MockHttpServletRequest request;

		UrlPathHelper urlPathHelper = new UrlPathHelper();
		urlPathHelper.setUrlDecode(false);
		urlPathHelper.setRemoveSemicolonContent(false);

		this.handlerMapping.setUrlPathHelper(urlPathHelper );

		request = new MockHttpServletRequest();
		handleMatch(request, "/path{filter}", "/path;mvar=a%2fb");

		MultiValueMap<String, String> matrixVariables = getMatrixVariables(request, "filter");
		Map<String, String> uriVariables = getUriTemplateVariables(request);

		assertNotNull(matrixVariables);
		assertEquals(Collections.singletonList("a/b"), matrixVariables.get("mvar"));
		assertEquals(";mvar=a/b", uriVariables.get("filter"));
	}
