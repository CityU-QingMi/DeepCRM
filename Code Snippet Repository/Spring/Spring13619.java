	@Test
	public void nestedTags() throws Exception {
		this.writer.startTag("span");
		this.writer.writeAttribute("style", "foo");
		this.writer.startTag("strong");
		this.writer.appendValue("Rob Harrop");
		this.writer.endTag();
		this.writer.endTag();

		assertEquals("<span style=\"foo\"><strong>Rob Harrop</strong></span>", this.data.toString());
	}
