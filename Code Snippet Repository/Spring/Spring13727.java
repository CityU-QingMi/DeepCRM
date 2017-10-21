	@Test
	public void renderSimpleMap() throws Exception {
		Map<String, Object> model = new HashMap<>();
		model.put("bindingResult", mock(BindingResult.class, "binding_result"));
		model.put("foo", "bar");

		view.setUpdateContentLength(true);
		view.render(model, request, response);

		assertEquals("no-store", response.getHeader("Cache-Control"));

		assertEquals(MappingJackson2JsonView.DEFAULT_CONTENT_TYPE, response.getContentType());

		String jsonResult = response.getContentAsString();
		assertTrue(jsonResult.length() > 0);
		assertEquals(jsonResult.length(), response.getContentLength());

		validateResult();
	}
