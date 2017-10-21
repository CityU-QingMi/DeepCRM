	@Test
	public void write() throws IOException, SAXException {
		Feed feed = new Feed("atom_1.0");
		feed.setTitle("title");

		Entry entry1 = new Entry();
		entry1.setId("id1");
		entry1.setTitle("title1");

		Entry entry2 = new Entry();
		entry2.setId("id2");
		entry2.setTitle("title2");

		List<Entry> entries = new ArrayList<>(2);
		entries.add(entry1);
		entries.add(entry2);
		feed.setEntries(entries);

		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		converter.write(feed, null, outputMessage);

		assertEquals("Invalid content-type", new MediaType("application", "atom+xml", StandardCharsets.UTF_8),
				outputMessage.getHeaders().getContentType());
		String expected = "<feed xmlns=\"http://www.w3.org/2005/Atom\">" + "<title>title</title>" +
				"<entry><id>id1</id><title>title1</title></entry>" +
				"<entry><id>id2</id><title>title2</title></entry></feed>";
		NodeMatcher nm = new DefaultNodeMatcher(ElementSelectors.byName);
		assertThat(outputMessage.getBodyAsString(StandardCharsets.UTF_8),
				isSimilarTo(expected).ignoreWhitespace().withNodeMatcher(nm));
	}
