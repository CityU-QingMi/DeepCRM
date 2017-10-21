	@Test
	public void withMultiValueWithReverseEditor() throws Exception {
		this.tag.setPath("stringArray");
		this.tag.setItems(new Object[] {"FOO", "BAR", "BAZ"});
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(this.bean, COMMAND_NAME);
		MyLowerCaseEditor editor = new MyLowerCaseEditor();
		bindingResult.getPropertyEditorRegistry().registerCustomEditor(String.class, editor);
		getPageContext().getRequest().setAttribute(BindingResult.MODEL_KEY_PREFIX + COMMAND_NAME, bindingResult);

		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);

		String output = getOutput();

		// wrap the output so it is valid XML
		output = "<doc>" + output + "</doc>";

		SAXReader reader = new SAXReader();
		Document document = reader.read(new StringReader(output));
		Element spanElement1 = (Element) document.getRootElement().elements().get(0);
		Element checkboxElement1 = (Element) spanElement1.elements().get(0);
		assertEquals("input", checkboxElement1.getName());
		assertEquals("checkbox", checkboxElement1.attribute("type").getValue());
		assertEquals("stringArray", checkboxElement1.attribute("name").getValue());
		assertEquals("checked", checkboxElement1.attribute("checked").getValue());
		assertEquals("FOO", checkboxElement1.attribute("value").getValue());
		Element spanElement2 = (Element) document.getRootElement().elements().get(1);
		Element checkboxElement2 = (Element) spanElement2.elements().get(0);
		assertEquals("input", checkboxElement2.getName());
		assertEquals("checkbox", checkboxElement2.attribute("type").getValue());
		assertEquals("stringArray", checkboxElement2.attribute("name").getValue());
		assertEquals("checked", checkboxElement2.attribute("checked").getValue());
		assertEquals("BAR", checkboxElement2.attribute("value").getValue());
		Element spanElement3 = (Element) document.getRootElement().elements().get(2);
		Element checkboxElement3 = (Element) spanElement3.elements().get(0);
		assertEquals("input", checkboxElement3.getName());
		assertEquals("checkbox", checkboxElement3.attribute("type").getValue());
		assertEquals("stringArray", checkboxElement3.attribute("name").getValue());
		assertNull("not checked", checkboxElement3.attribute("checked"));
		assertEquals("BAZ", checkboxElement3.attribute("value").getValue());
	}
