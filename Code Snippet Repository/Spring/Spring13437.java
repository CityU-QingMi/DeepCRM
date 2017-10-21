	@Test
	public void withSingleValueAndEditor() throws Exception {
		this.bean.setName("Rob Harrop");
		this.tag.setPath("name");
		this.tag.setValue("   Rob Harrop");
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(this.bean, COMMAND_NAME);
		bindingResult.getPropertyEditorRegistry().registerCustomEditor(String.class, new StringTrimmerEditor(false));
		getPageContext().getRequest().setAttribute(BindingResult.MODEL_KEY_PREFIX + COMMAND_NAME, bindingResult);

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
		assertEquals("name", checkboxElement.attribute("name").getValue());
		assertEquals("checked", checkboxElement.attribute("checked").getValue());
		assertEquals("   Rob Harrop", checkboxElement.attribute("value").getValue());
	}
