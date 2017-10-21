	@Test
	public void withItemsNullReference() throws Exception {
		getPageContext().setAttribute(
				SelectTag.LIST_VALUE_PAGE_ATTRIBUTE, new BindStatus(getRequestContext(), "testBean.country", false));

		this.tag.setItems(Collections.emptyList());
		this.tag.setItemValue("isoCode");
		this.tag.setItemLabel("name");
		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);
		String output = getOutput();
		output = "<doc>" + output + "</doc>";

		SAXReader reader = new SAXReader();
		Document document = reader.read(new StringReader(output));
		Element rootElement = document.getRootElement();

		List children = rootElement.elements();
		assertEquals("Incorrect number of children", 0, children.size());
	}
