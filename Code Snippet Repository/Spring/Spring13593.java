	@Test
	public void withIntegerArray() throws Exception {
		this.tag.setPath("someIntegerArray");
		Integer[] array = new Integer[50];
		for (int i = 0; i < array.length; i++) {
			array[i] = new Integer(i);
		}
		this.tag.setItems(array);
		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);

		String output = getOutput();
		output = "<doc>" + output + "</doc>";

		SAXReader reader = new SAXReader();
		Document document = reader.read(new StringReader(output));
		Element rootElement = document.getRootElement();
		assertEquals(2, rootElement.elements().size());

		Element selectElement = rootElement.element("select");
		assertEquals("select", selectElement.getName());
		assertEquals("someIntegerArray", selectElement.attribute("name").getValue());

		List children = selectElement.elements();
		assertEquals("Incorrect number of children", array.length, children.size());

		Element e = (Element) selectElement.selectSingleNode("option[text() = '12']");
		assertEquals("'12' node not selected", "selected", e.attribute("selected").getValue());

		e = (Element) selectElement.selectSingleNode("option[text() = '34']");
		assertEquals("'34' node not selected", "selected", e.attribute("selected").getValue());
	}
