	@Test
	public void withoutItemsEnumBindTarget() throws Exception {
		BeanWithEnum testBean = new BeanWithEnum();
		testBean.setTestEnum(TestEnum.VALUE_2);
		getPageContext().getRequest().setAttribute("testBean", testBean);

		this.tag.setPath("testEnum");
		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);

		String output = "<div>" + getOutput() + "</div>";
		SAXReader reader = new SAXReader();
		Document document = reader.read(new StringReader(output));
		Element rootElement = document.getRootElement();

		assertEquals(2, rootElement.elements().size());
		Node value1 = rootElement.selectSingleNode("//input[@value = 'VALUE_1']");
		Node value2 = rootElement.selectSingleNode("//input[@value = 'VALUE_2']");
		assertEquals("TestEnum: VALUE_1", rootElement.selectSingleNode("//label[@for = '" + value1.valueOf("@id") + "']").getText());
		assertEquals("TestEnum: VALUE_2", rootElement.selectSingleNode("//label[@for = '" + value2.valueOf("@id") + "']").getText());
		assertEquals(value2, rootElement.selectSingleNode("//input[@checked]"));
	}
