	@Test
	public void write() throws IOException, SAXException {
		Channel channel = new Channel("rss_2.0");
		channel.setTitle("title");
		channel.setLink("http://example.com");
		channel.setDescription("description");

		Item item1 = new Item();
		item1.setTitle("title1");

		Item item2 = new Item();
		item2.setTitle("title2");

		List<Item> items = new ArrayList<>(2);
		items.add(item1);
		items.add(item2);
		channel.setItems(items);

		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		converter.write(channel, null, outputMessage);

		assertEquals("Invalid content-type", new MediaType("application", "rss+xml", StandardCharsets.UTF_8),
				outputMessage.getHeaders().getContentType());
		String expected = "<rss version=\"2.0\">" +
				"<channel><title>title</title><link>http://example.com</link><description>description</description>" +
				"<item><title>title1</title></item>" +
				"<item><title>title2</title></item>" +
				"</channel></rss>";
		assertThat(outputMessage.getBodyAsString(StandardCharsets.UTF_8), isSimilarTo(expected));
	}
