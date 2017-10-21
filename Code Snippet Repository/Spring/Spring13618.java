	@Test
	public void blockTagWithAttributes() throws Exception {
		this.writer.startTag("textarea");
		this.writer.writeAttribute("width", "10");
		this.writer.writeAttribute("height", "20");
		this.writer.appendValue("foobar");
		this.writer.endTag();

		assertEquals("<textarea width=\"10\" height=\"20\">foobar</textarea>", this.data.toString());
	}
