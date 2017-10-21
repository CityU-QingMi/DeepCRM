	@Test
	public void multipleExplicitlyTrue() throws Exception {
		this.tag.setPath("name");
		this.tag.setItems(Country.getCountries());
		this.tag.setItemValue("isoCode");
		this.tag.setMultiple("true");
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
		assertEquals("name", selectElement.attribute("name").getValue());
		assertEquals("multiple", selectElement.attribute("multiple").getValue());

		List children = selectElement.elements();
		assertEquals("Incorrect number of children", 4, children.size());

		Element inputElement = rootElement.element("input");
		assertNotNull(inputElement);
	}
