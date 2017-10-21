	@Test
	public void testStaticAttributesCarriedAcross() throws Exception {
		XsltView view = getXsltView(HTML_OUTPUT);
		view.setSourceKey("actualData");
		view.addStaticAttribute("title", "Product List");

		Map<String, Object> model = new HashMap<>();
		model.put("actualData", getProductDataResource());
		model.put("otherData", new ClassPathResource("dummyData.xsl", getClass()));

		view.render(model, this.request, this.response);
		assertHtmlOutput(this.response.getContentAsString());
		assertTrue(this.response.getContentAsString().indexOf("Product List") > -1);

	}
