	@Test
	public void withFloatCustom() throws Exception {
		PropertyEditor propertyEditor = new SimpleFloatEditor();
		BeanPropertyBindingResult errors = new BeanPropertyBindingResult(getTestBean(), COMMAND_NAME);
		errors.getPropertyAccessor().registerCustomEditor(Float.class, propertyEditor);
		exposeBindingResult(errors);

		this.tag.setPath("myFloat");

		Float[] array = new Float[] {
				new Float("12.30"), new Float("12.32"), new Float("12.34"), new Float("12.36"),
				new Float("12.38"), new Float("12.40"), new Float("12.42"), new Float("12.44"),
				new Float("12.46"), new Float("12.48")
		};

		this.tag.setItems(array);
		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);

		String output = getOutput();
		assertTrue(output.startsWith("<select "));
		assertTrue(output.endsWith("</select>"));

		SAXReader reader = new SAXReader();
		Document document = reader.read(new StringReader(output));
		Element rootElement = document.getRootElement();
		assertEquals("select", rootElement.getName());
		assertEquals("myFloat", rootElement.attribute("name").getValue());
		List children = rootElement.elements();
		assertEquals("Incorrect number of children", array.length, children.size());

		Element e = (Element) rootElement.selectSingleNode("option[text() = '12.34f']");
		assertEquals("'12.34' node not selected", "selected", e.attribute("selected").getValue());

		e = (Element) rootElement.selectSingleNode("option[text() = '12.32f']");
		assertNull("'12.32' node incorrectly selected", e.attribute("selected"));
	}
