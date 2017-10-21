	@Test
	public void withoutItems() throws Exception {
		this.tag.setItemValue("isoCode");
		this.tag.setItemLabel("name");
		this.selectTag.setPath("testBean");

		this.selectTag.doStartTag();
		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);
		this.tag.doEndTag();
		this.selectTag.doEndTag();

		String output = getOutput();
		SAXReader reader = new SAXReader();
		Document document = reader.read(new StringReader(output));
		Element rootElement = document.getRootElement();

		List children = rootElement.elements();
		assertEquals("Incorrect number of children", 0, children.size());
	}
