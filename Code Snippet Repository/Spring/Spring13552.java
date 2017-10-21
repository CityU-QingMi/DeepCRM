	@Test
	public void withCollection() throws Exception {
		getPageContext().setAttribute(
				SelectTag.LIST_VALUE_PAGE_ATTRIBUTE, new BindStatus(getRequestContext(), "testBean.country", false));

		this.tag.setItems(Country.getCountries());
		this.tag.setItemValue("isoCode");
		this.tag.setItemLabel("name");
		this.tag.setId("myOption");
		this.tag.setCssClass("myClass");
		this.tag.setOnclick("CLICK");
		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);
		String output = getOutput();
		output = "<doc>" + output + "</doc>";

		SAXReader reader = new SAXReader();
		Document document = reader.read(new StringReader(output));
		Element rootElement = document.getRootElement();

		List children = rootElement.elements();
		assertEquals("Incorrect number of children", 4, children.size());

		Element element = (Element) rootElement.selectSingleNode("option[@value = 'UK']");
		assertEquals("UK node not selected", "selected", element.attribute("selected").getValue());
		assertEquals("myOption3", element.attribute("id").getValue());
		assertEquals("myClass", element.attribute("class").getValue());
		assertEquals("CLICK", element.attribute("onclick").getValue());
	}
