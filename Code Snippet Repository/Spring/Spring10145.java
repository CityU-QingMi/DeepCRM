	@Test
	public void read() throws IOException {
		InputStream is = getClass().getResourceAsStream("rss.xml");
		MockHttpInputMessage inputMessage = new MockHttpInputMessage(is);
		inputMessage.getHeaders().setContentType(new MediaType("application", "rss+xml", StandardCharsets.UTF_8));
		Channel result = converter.read(Channel.class, inputMessage);
		assertEquals("title", result.getTitle());
		assertEquals("http://example.com", result.getLink());
		assertEquals("description", result.getDescription());

		List<?> items = result.getItems();
		assertEquals(2, items.size());

		Item item1 = (Item) items.get(0);
		assertEquals("title1", item1.getTitle());

		Item item2 = (Item) items.get(1);
		assertEquals("title2", item2.getTitle());
	}
