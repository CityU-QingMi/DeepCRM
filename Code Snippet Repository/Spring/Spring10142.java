	@Test
	public void read() throws IOException {
		InputStream is = getClass().getResourceAsStream("atom.xml");
		MockHttpInputMessage inputMessage = new MockHttpInputMessage(is);
		inputMessage.getHeaders().setContentType(new MediaType("application", "atom+xml", StandardCharsets.UTF_8));
		Feed result = converter.read(Feed.class, inputMessage);
		assertEquals("title", result.getTitle());
		assertEquals("subtitle", result.getSubtitle().getValue());
		List<?> entries = result.getEntries();
		assertEquals(2, entries.size());

		Entry entry1 = (Entry) entries.get(0);
		assertEquals("id1", entry1.getId());
		assertEquals("title1", entry1.getTitle());

		Entry entry2 = (Entry) entries.get(1);
		assertEquals("id2", entry2.getId());
		assertEquals("title2", entry2.getTitle());
	}
