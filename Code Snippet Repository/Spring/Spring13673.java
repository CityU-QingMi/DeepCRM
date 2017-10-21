	@Test
	public void uriTemplateAndArrayQueryParam() throws Exception {
		Map<String, Object> model = new HashMap<>();
		model.put("foo", "bar");
		model.put("fooArr", new String[] { "baz", "bazz" });

		RedirectView redirectView = new RedirectView("/foo/{foo}");
		redirectView.renderMergedOutputModel(model, this.request, this.response);

		assertEquals("/foo/bar?fooArr=baz&fooArr=bazz", this.response.getRedirectedUrl());
	}
