	@Test
	public void uriTemplateWithObjectConversion() throws Exception {
		Map<String, Object> model = new HashMap<>();
		model.put("foo", new Long(611));

		RedirectView redirectView = new RedirectView("/foo/{foo}");
		redirectView.renderMergedOutputModel(model, this.request, this.response);

		assertEquals("/foo/611", this.response.getRedirectedUrl());
	}
