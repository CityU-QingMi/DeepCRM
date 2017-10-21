	@Test
	public void collectionOfPets() throws Exception {
		this.tag.setPath("pets");
		this.tag.setValue(new Pet("Rudiger"));

		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);

		String output = getOutput();

		// wrap the output so it is valid XML
		output = "<doc>" + output + "</doc>";

		SAXReader reader = new SAXReader();
		Document document = reader.read(new StringReader(output));
		Element checkboxElement = (Element) document.getRootElement().elements().get(0);
		assertEquals("input", checkboxElement.getName());
		assertEquals("radio", checkboxElement.attribute("type").getValue());
		assertEquals("pets", checkboxElement.attribute("name").getValue());
		assertEquals("Rudiger", checkboxElement.attribute("value").getValue());
		assertEquals("checked", checkboxElement.attribute("checked").getValue());
	}
