	@Test
	public void renderTemplateWithoutRenderFunction() throws Exception {
		Map<String, Object> model = new HashMap<>();
		model.put("header", "<html><body>");
		model.put("hello", "Hello");
		model.put("foo", "Foo");
		model.put("footer", "</body></html>");
		MockServerHttpResponse response = renderViewWithModel("org/springframework/web/reactive/result/view/script/kotlin/eval.kts",
				model, Locale.ENGLISH, ScriptTemplatingConfigurationWithoutRenderFunction.class);
		assertEquals("<html><body>\n<p>Hello Foo</p>\n</body></html>",
				response.getBodyAsString().block());
	}
