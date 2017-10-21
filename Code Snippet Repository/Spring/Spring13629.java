	@Test
	public void renderWithoutStaticAttributes() throws Exception {
		WebApplicationContext wac = mock(WebApplicationContext.class);
		given(wac.getServletContext()).willReturn(new MockServletContext());

		HttpServletRequest request = new MockHttpServletRequest();
		HttpServletResponse response = new MockHttpServletResponse();
		TestView tv = new TestView(wac);

		// Check superclass handles duplicate init
		tv.setApplicationContext(wac);
		tv.setApplicationContext(wac);

		Map<String, Object> model = new HashMap<>();
		model.put("foo", "bar");
		model.put("something", new Object());
		tv.render(model, request, response);

		checkContainsAll(model, tv.model);

		assertTrue(tv.initialized);
	}
