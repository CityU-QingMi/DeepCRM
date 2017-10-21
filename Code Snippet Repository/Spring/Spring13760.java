	@Test
	public void renderInvalidModelKey() throws Exception {
		Object toBeMarshalled = new Object();
		String modelKey = "key";
		view.setModelKey("invalidKey");
		Map<String, Object> model = new HashMap<>();
		model.put(modelKey, toBeMarshalled);

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
