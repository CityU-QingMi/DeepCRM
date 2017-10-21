	@Test
	public void renderWithPrettyPrint() throws Exception {
		ModelMap model = new ModelMap("foo", new TestBeanSimple());

		view.setPrettyPrint(true);
		view.render(model, request, response);

		String result = response.getContentAsString().replace("\r\n", "\n");
		assertTrue("Pretty printing not applied:\n" + result, result.startsWith("{\n  \"foo\" : {\n    "));

		validateResult();
	}
