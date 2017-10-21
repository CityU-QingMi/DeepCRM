	@Test
	public void renderOnlySpecifiedModelKey() throws Exception {

		view.setModelKey("bar");
		Map<String, Object> model = new HashMap<>();
		model.put("foo", "foo");
		model.put("bar", "bar");
		model.put("baz", "baz");

		view.render(model, request, response);

		String result = response.getContentAsString();
		assertTrue(result.length() > 0);
		assertFalse(result.contains("foo"));
		assertTrue(result.contains("bar"));
		assertFalse(result.contains("baz"));

		validateResult();
	}
