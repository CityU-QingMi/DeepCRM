	@Test
	public void withMultiList() throws Exception {
		List list = new ArrayList();
		list.add(Country.COUNTRY_UK);
		list.add(Country.COUNTRY_AT);
		this.bean.setSomeList(list);

		this.tag.setPath("someList");
		this.tag.setItems(Country.getCountries());
		this.tag.setItemValue("isoCode");
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
		assertEquals("someList", selectElement.attribute("name").getValue());

		List children = selectElement.elements();
		assertEquals("Incorrect number of children", 4, children.size());

		Element e = (Element) selectElement.selectSingleNode("option[@value = 'UK']");
		assertEquals("UK node not selected", "selected", e.attribute("selected").getValue());
		assertEquals("United Kingdom(UK)", e.getText());

		e = (Element) selectElement.selectSingleNode("option[@value = 'AT']");
		assertEquals("AT node not selected", "selected", e.attribute("selected").getValue());
		assertEquals("Austria(AT)", e.getText());
	}
