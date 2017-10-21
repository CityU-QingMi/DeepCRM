	@Test
	public void withObjectUnchecked() throws Exception {
		this.tag.setPath("date");
		Date date = new Date();
		this.tag.setValue(date);

		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);

		String output = getOutput();

		// wrap the output so it is valid XML
		output = "<doc>" + output + "</doc>";

		SAXReader reader = new SAXReader();
		Document document = reader.read(new StringReader(output));
		Element checkboxElement = (Element) document.getRootElement().elements().get(0);
		assertEquals("input", checkboxElement.getName());
		assertEquals("checkbox", checkboxElement.attribute("type").getValue());
		assertEquals("date", checkboxElement.attribute("name").getValue());
		assertNull(checkboxElement.attribute("checked"));
		assertEquals(date.toString(), checkboxElement.attribute("value").getValue());
	}
