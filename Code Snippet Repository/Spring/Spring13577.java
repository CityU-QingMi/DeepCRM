	@Test
	public void hiddenElementOmittedOnDisabled() throws Exception {
		this.tag.setPath("stringArray");
		this.tag.setItems(new Object[] {"foo", "bar", "baz"});
		this.tag.setDisabled(true);
		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);
		String output = getOutput();

		// wrap the output so it is valid XML
		output = "<doc>" + output + "</doc>";

		SAXReader reader = new SAXReader();
		Document document = reader.read(new StringReader(output));
		Element rootElement = document.getRootElement();
		assertEquals("Both tag and hidden element rendered incorrectly", 3, rootElement.elements().size());
		Element spanElement = (Element) document.getRootElement().elements().get(0);
		Element radioButtonElement = (Element) spanElement.elements().get(0);
		assertEquals("input", radioButtonElement.getName());
		assertEquals("radio", radioButtonElement.attribute("type").getValue());
		assertEquals("stringArray", radioButtonElement.attribute("name").getValue());
		assertEquals("checked", radioButtonElement.attribute("checked").getValue());
		assertEquals("disabled", radioButtonElement.attribute("disabled").getValue());
		assertEquals("foo", radioButtonElement.attribute("value").getValue());
	}
