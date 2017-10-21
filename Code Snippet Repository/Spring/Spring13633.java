	@Test
	public void dynamicModelOverridesPathVariables() throws Exception {
		WebApplicationContext wac = mock(WebApplicationContext.class);
		given(wac.getServletContext()).willReturn(new MockServletContext());

		TestView tv = new TestView(wac);
		tv.setApplicationContext(wac);

		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();

		Map<String, Object> pathVars = new HashMap<>();
		pathVars.put("one", "bar");
		pathVars.put("something", "else");
		request.setAttribute(View.PATH_VARIABLES, pathVars);

		Map<String, Object> model = new HashMap<>();
		model.put("one", new HashMap<>());
		model.put("two", new Object());

		tv.render(model, request, response);

		checkContainsAll(model, tv.model);
		assertEquals(3, tv.model.size());
		assertEquals("else", tv.model.get("something"));
		assertTrue(tv.initialized);
	}
