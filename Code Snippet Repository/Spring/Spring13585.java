	@Test
	public void withMultiValueWithEditor() throws Exception {
		this.tag.setPath("stringArray");
		this.tag.setItems(new Object[] {"   foo", "   bar", "   baz"});
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(this.bean, COMMAND_NAME);
		MyStringTrimmerEditor editor = new MyStringTrimmerEditor();
		bindingResult.getPropertyEditorRegistry().registerCustomEditor(String.class, editor);
		getPageContext().getRequest().setAttribute(BindingResult.MODEL_KEY_PREFIX + COMMAND_NAME, bindingResult);

		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);
		assertEquals(3, editor.allProcessedValues.size());

		String output = getOutput();

		// wrap the output so it is valid XML
		output = "<doc>" + output + "</doc>";

		SAXReader reader = new SAXReader();
		Document document = reader.read(new StringReader(output));
		Element spanElement1 = (Element) document.getRootElement().elements().get(0);
		Element radioButtonElement1 = (Element) spanElement1.elements().get(0);
		assertEquals("input", radioButtonElement1.getName());
		assertEquals("radio", radioButtonElement1.attribute("type").getValue());
		assertEquals("stringArray", radioButtonElement1.attribute("name").getValue());
		assertEquals("checked", radioButtonElement1.attribute("checked").getValue());
		assertEquals("   foo", radioButtonElement1.attribute("value").getValue());
		Element spanElement2 = (Element) document.getRootElement().elements().get(1);
		Element radioButtonElement2 = (Element) spanElement2.elements().get(0);
		assertEquals("input", radioButtonElement2.getName());
		assertEquals("radio", radioButtonElement2.attribute("type").getValue());
		assertEquals("stringArray", radioButtonElement2.attribute("name").getValue());
		assertEquals("checked", radioButtonElement2.attribute("checked").getValue());
		assertEquals("   bar", radioButtonElement2.attribute("value").getValue());
		Element spanElement3 = (Element) document.getRootElement().elements().get(2);
		Element radioButtonElement3 = (Element) spanElement3.elements().get(0);
		assertEquals("input", radioButtonElement3.getName());
		assertEquals("radio", radioButtonElement3.attribute("type").getValue());
		assertEquals("stringArray", radioButtonElement3.attribute("name").getValue());
		assertNull("not checked", radioButtonElement3.attribute("checked"));
		assertEquals("   baz", radioButtonElement3.attribute("value").getValue());
	}
