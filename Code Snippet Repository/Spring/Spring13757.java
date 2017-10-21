	@Test(expected = IllegalStateException.class)
	public void renderModelWithMultipleKeys() throws Exception {

		Map<String, Object> model = new TreeMap<>();
		model.put("foo", "foo");
		model.put("bar", "bar");

		view.render(model, request, response);

		fail();
	}
