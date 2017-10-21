	@Test
	public void uriTemplateReuseCurrentRequestVars() throws Exception {
		Map<String, Object> model = new HashMap<>();
		model.put("key1", "value1");
		model.put("name", "value2");
		model.put("key3", "value3");

		Map<String, String> currentRequestUriTemplateVars = new HashMap<>();
		currentRequestUriTemplateVars.put("var1", "v1");
		currentRequestUriTemplateVars.put("name", "v2");
		currentRequestUriTemplateVars.put("var3", "v3");
		this.request.setAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, currentRequestUriTemplateVars);

		String url = "http://url.somewhere.com";
		RedirectView redirectView = new RedirectView(url + "/{key1}/{var1}/{name}");
		redirectView.renderMergedOutputModel(model, this.request, this.response);

		assertEquals(url + "/value1/v1/value2?key3=value3", this.response.getRedirectedUrl());
	}
