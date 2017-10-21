	@Test
	public void withoutItemsEnumParentWithExplicitLabelsAndValues() throws Exception {
		BeanWithEnum testBean = new BeanWithEnum();
		testBean.setTestEnum(TestEnum.VALUE_2);
		getPageContext().getRequest().setAttribute("testBean", testBean);

		this.selectTag.setPath("testBean.testEnum");
		this.tag.setItemLabel("enumLabel");
		this.tag.setItemValue("enumValue");

		this.selectTag.doStartTag();
		int result = this.tag.doStartTag();
		assertEquals(BodyTag.SKIP_BODY, result);
		result = this.tag.doEndTag();
		assertEquals(Tag.EVAL_PAGE, result);
		this.selectTag.doEndTag();

		String output = getWriter().toString();
		SAXReader reader = new SAXReader();
		Document document = reader.read(new StringReader(output));
		Element rootElement = document.getRootElement();

		assertEquals(2, rootElement.elements().size());
		Node value1 = rootElement.selectSingleNode("option[@value = 'Value: VALUE_1']");
		Node value2 = rootElement.selectSingleNode("option[@value = 'Value: VALUE_2']");
		assertEquals("Label: VALUE_1", value1.getText());
		assertEquals("Label: VALUE_2", value2.getText());
		assertEquals(value2, rootElement.selectSingleNode("option[@selected]"));
	}
