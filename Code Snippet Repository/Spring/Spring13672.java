	@Test
	public void uriTemplateEncode() throws Exception {
		Map<String, Object> model = new HashMap<>();
		model.put("foo", "bar/bar baz");

		String baseUrl = "http://url.somewhere.com";
		RedirectView redirectView = new RedirectView(baseUrl + "/context path/{foo}");
		redirectView.renderMergedOutputModel(model, this.request, this.response);

		assertEquals(baseUrl + "/context path/bar%2Fbar%20baz", this.response.getRedirectedUrl());
	}
