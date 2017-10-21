	private void validateOutput(String output, boolean selected) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new StringReader(output));
		Element rootElement = document.getRootElement();
		assertEquals("select", rootElement.getName());
		assertEquals("country", rootElement.attribute("name").getValue());

		List children = rootElement.elements();
		assertEquals("Incorrect number of children", 4, children.size());

		Element e = (Element) rootElement.selectSingleNode("option[@value = 'UK']");
		Attribute selectedAttr = e.attribute("selected");
		if (selected) {
			assertTrue(selectedAttr != null && "selected".equals(selectedAttr.getValue()));
		}
		else {
			assertNull(selectedAttr);
		}
	}
