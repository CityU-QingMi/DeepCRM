	@Test
	public void renderSimpleBean() throws Exception {
		Object bean = new TestBeanSimple();
		Map<String, Object> model = new HashMap<>();
		model.put("bindingResult", mock(BindingResult.class, "binding_result"));
		model.put("foo", bean);

		view.setUpdateContentLength(true);
		view.render(model, request, response);

		assertTrue(response.getContentAsString().length() > 0);
		assertEquals(response.getContentAsString().length(), response.getContentLength());

		validateResult();
	}
