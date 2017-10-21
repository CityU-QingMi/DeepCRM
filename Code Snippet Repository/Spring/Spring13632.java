	@Test
	public void dynamicModelOverridesStaticAttributesIfCollision() throws Exception {
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

		Map<String, Object> model = new HashMap<>();
		model.put("one", new HashMap<>());
		model.put("two", new Object());
		tv.render(model, request, response);

		// Check it contains all
		checkContainsAll(model, tv.model);

		assertEquals(3, tv.model.size());
		assertEquals("else", tv.model.get("something"));
		assertTrue(tv.initialized);
	}
