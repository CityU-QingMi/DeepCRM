	@Test
	public void testWithSourceKey() throws Exception {
		XsltView view = getXsltView(HTML_OUTPUT);
		view.setSourceKey("actualData");

		Map<String, Object> model = new HashMap<>();
		model.put("actualData", getProductDataResource());
		model.put("otherData", new ClassPathResource("dummyData.xsl", getClass()));

		view.render(model, this.request, this.response);
		assertHtmlOutput(this.response.getContentAsString());
	}
