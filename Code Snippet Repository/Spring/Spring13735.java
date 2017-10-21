	@Test
	public void renderTemplate() throws Exception {
		Map<String, Object> model = new HashMap<>();
		model.put("title", "Layout example");
		model.put("body", "This is the body");
		MockHttpServletResponse response = renderViewWithModel("org/springframework/web/servlet/view/script/nashorn/template.html",
				model, ScriptTemplatingConfiguration.class);
		assertEquals("<html><head><title>Layout example</title></head><body><p>This is the body</p></body></html>",
				response.getContentAsString());
	}
