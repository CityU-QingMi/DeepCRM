	private void assertStringArray() throws JspException, DocumentException {
		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);

		String output = getOutput();
		assertTrue(output.startsWith("<select "));
		assertTrue(output.endsWith("</select>"));

		SAXReader reader = new SAXReader();
		Document document = reader.read(new StringReader(output));
		Element rootElement = document.getRootElement();
		assertEquals("select", rootElement.getName());
		assertEquals("name", rootElement.attribute("name").getValue());

		List children = rootElement.elements();
		assertEquals("Incorrect number of children", 4, children.size());

		Element e = (Element) rootElement.selectSingleNode("option[text() = 'Rob']");
		assertEquals("Rob node not selected", "selected", e.attribute("selected").getValue());
	}
