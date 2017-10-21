	@Test
	public void pathVarsOverrideStaticAttributes() throws Exception {
		WebApplicationContext wac = mock(WebApplicationContext.class);
		given(wac.getServletContext()).willReturn(new MockServletContext());

		HttpServletRequest request = new MockHttpServletRequest();
		HttpServletResponse response = new MockHttpServletResponse();

		TestView tv = new TestView(wac);
		tv.setApplicationContext(wac);

		Properties p = new Properties();
		p.setProperty("one", "bar");
		p.setProperty("something", "else");
		tv.setAttributes(p);

		Map<String, Object> pathVars = new HashMap<>();
		pathVars.put("one", new HashMap<>());
		pathVars.put("two", new Object());
		request.setAttribute(View.PATH_VARIABLES, pathVars);

		tv.render(new HashMap<>(), request, response);

		checkContainsAll(pathVars, tv.model);

		assertEquals(3, tv.model.size());
		assertEquals("else", tv.model.get("something"));
		assertTrue(tv.initialized);
	}
