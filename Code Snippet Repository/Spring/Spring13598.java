	@Test
	public void withMultiMap() throws Exception {
		Map someMap = new HashMap();
		someMap.put("M", "Male");
		someMap.put("F", "Female");
		this.bean.setSomeMap(someMap);

		this.tag.setPath("someMap");
		this.tag.setItems(getSexes());

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
		assertEquals("someMap", selectElement.attribute("name").getValue());

		List children = selectElement.elements();
		assertEquals("Incorrect number of children", 2, children.size());

		Element e = (Element) selectElement.selectSingleNode("option[@value = 'M']");
		assertEquals("M node not selected", "selected", e.attribute("selected").getValue());

		e = (Element) selectElement.selectSingleNode("option[@value = 'F']");
		assertEquals("F node not selected", "selected", e.attribute("selected").getValue());
	}
