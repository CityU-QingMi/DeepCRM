	@Test
	public void renderWithStaticAttributesNoCollision() throws Exception {
		WebApplicationContext wac = mock(WebApplicationContext.class);
		given(wac.getServletContext()).willReturn(new MockServletContext());

		HttpServletRequest request = new MockHttpServletRequest();
		HttpServletResponse response = new MockHttpServletResponse();
		TestView tv = new TestView(wac);

		tv.setApplicationContext(wac);
		Properties p = new Properties();
		p.setProperty("foo", "bar");
		p.setProperty("something", "else");
		tv.setAttributes(p);

		Map<String, Object> model = new HashMap<>();
		model.put("one", new HashMap<>());
		model.put("two", new Object());
		tv.render(model, request, response);

		checkContainsAll(model, tv.model);
		checkContainsAll(p, tv.model);

		assertTrue(tv.initialized);
	}
