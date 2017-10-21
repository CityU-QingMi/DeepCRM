	@Test
	public void renderOnlyIncludedAttributes() throws Exception {

		Set<String> attrs = new HashSet<>();
		attrs.add("foo");
		attrs.add("baz");
		attrs.add("nil");

		view.setModelKeys(attrs);
		Map<String, Object> model = new HashMap<>();
		model.put("foo", "foo");
		model.put("bar", "bar");
		model.put("baz", "baz");

		view.render(model, request, response);

		String result = response.getContentAsString();
		assertTrue(result.length() > 0);
		assertTrue(result.contains("\"foo\":\"foo\""));
		assertTrue(result.contains("\"baz\":\"baz\""));

		validateResult();
	}
