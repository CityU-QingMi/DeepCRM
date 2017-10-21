	@Test
	public void renderSimpleBeanWithJsonView() throws Exception {
		Object bean = new TestBeanSimple();
		Map<String, Object> model = new HashMap<>();
		model.put("bindingResult", mock(BindingResult.class, "binding_result"));
		model.put("foo", bean);
		model.put(JsonView.class.getName(), MyJacksonView1.class);

		view.setUpdateContentLength(true);
		view.render(model, request, response);

		String content = response.getContentAsString();
		assertTrue(content.length() > 0);
		assertEquals(content.length(), response.getContentLength());
		assertTrue(content.contains("foo"));
		assertFalse(content.contains("boo"));
		assertFalse(content.contains(JsonView.class.getName()));
	}
