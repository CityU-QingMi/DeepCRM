	@Test
	public void withIndexedBooleanObjectNotChecked() throws Exception {
		this.tag.setPath("someMap[key]");
		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);
		String output = getOutput();

		// wrap the output so it is valid XML
		output = "<doc>" + output + "</doc>";

		SAXReader reader = new SAXReader();
		Document document = reader.read(new StringReader(output));
		Element rootElement = document.getRootElement();
		assertEquals("Both tag and hidden element not rendered", 2, rootElement.elements().size());
		Element checkboxElement = (Element) rootElement.elements().get(0);
		assertEquals("input", checkboxElement.getName());
		assertEquals("checkbox", checkboxElement.attribute("type").getValue());
		assertEquals("someMapkey1", checkboxElement.attribute("id").getValue());
		assertEquals("someMap[key]", checkboxElement.attribute("name").getValue());
		assertEquals("checked", checkboxElement.attribute("checked").getValue());
		assertEquals("true", checkboxElement.attribute("value").getValue());
	}
