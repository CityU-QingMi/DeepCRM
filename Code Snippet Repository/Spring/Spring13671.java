	@Test
	public void uriTemplate() throws Exception {
		Map<String, Object> model = new HashMap<>();
		model.put("foo", "bar");

		String baseUrl = "http://url.somewhere.com";
		RedirectView redirectView = new RedirectView(baseUrl + "/{foo}");
		redirectView.renderMergedOutputModel(model, this.request, this.response);

		assertEquals(baseUrl + "/bar", this.response.getRedirectedUrl());
	}
