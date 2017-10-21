	@Test
	public void renderNullModelValue() throws Exception {
		String modelKey = "key";
		Map<String, Object> model = new HashMap<>();
		model.put(modelKey, null);

		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();

		try {
			view.render(model, request, response);
			fail("IllegalStateException expected");
		}
		catch (IllegalStateException ex) {
			// expected
		}
		assertEquals("Invalid content length", 0, response.getContentLength());
	}
