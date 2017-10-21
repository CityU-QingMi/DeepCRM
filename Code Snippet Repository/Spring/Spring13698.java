	@Test
	public void render() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();

		Map<String, String> model = new LinkedHashMap<>();
		model.put("2", "This is entry 2");
		model.put("1", "This is entry 1");

		view.render(model, request, response);
		assertEquals("Invalid content-type", "application/rss+xml", response.getContentType());
		String expected = "<rss version=\"2.0\">" +
				"<channel><title>Test Feed</title><link>http://example.com</link><description>Test feed description</description>" +
				"<item><title>2</title><description>This is entry 2</description></item>" +
				"<item><title>1</title><description>This is entry 1</description></item>" + "</channel></rss>";
		assertThat(response.getContentAsString(), isSimilarTo(expected).ignoreWhitespace());
	}
