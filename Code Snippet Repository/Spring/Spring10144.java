	@Test
	public void writeOtherCharset() throws IOException, SAXException {
		Feed feed = new Feed("atom_1.0");
		feed.setTitle("title");
		String encoding = "ISO-8859-1";
		feed.setEncoding(encoding);

		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		converter.write(feed, null, outputMessage);

		assertEquals("Invalid content-type", new MediaType("application", "atom+xml", Charset.forName(encoding)),
				outputMessage.getHeaders().getContentType());
	}
