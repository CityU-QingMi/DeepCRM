	@Test
	public void render() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();

		Map<String, String> model = new LinkedHashMap<>();
		model.put("2", "This is entry 2");
		model.put("1", "This is entry 1");

		view.render(model, request, response);
		assertEquals("Invalid content-type", "application/atom+xml", response.getContentType());
		String expected = "<feed xmlns=\"http://www.w3.org/2005/Atom\">" + "<title>Test Feed</title>" +
				"<entry><title>2</title><summary>This is entry 2</summary></entry>" +
				"<entry><title>1</title><summary>This is entry 1</summary></entry>" + "</feed>";
		assertThat(response.getContentAsString(), isSimilarTo(expected));
	}
