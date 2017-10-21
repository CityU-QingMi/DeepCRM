	@Test
	public void withCollectionAndCustomEditor() throws Exception {
		PropertyEditor propertyEditor = new SimpleFloatEditor();

		TestBean target = new TestBean();
		target.setMyFloat(new Float("12.34"));

		BeanPropertyBindingResult errors = new BeanPropertyBindingResult(target, COMMAND_NAME);
		errors.getPropertyAccessor().registerCustomEditor(Float.class, propertyEditor);
		exposeBindingResult(errors);

		getPageContext().setAttribute(
				SelectTag.LIST_VALUE_PAGE_ATTRIBUTE, new BindStatus(getRequestContext(), "testBean.myFloat", false));

		List<Float> floats = new ArrayList<>();
		floats.add(new Float("12.30"));
		floats.add(new Float("12.31"));
		floats.add(new Float("12.32"));
		floats.add(new Float("12.33"));
		floats.add(new Float("12.34"));
		floats.add(new Float("12.35"));

		this.tag.setItems(floats);
		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);
		String output = getOutput();
		output = "<doc>" + output + "</doc>";

		SAXReader reader = new SAXReader();
		Document document = reader.read(new StringReader(output));
		Element rootElement = document.getRootElement();

		List children = rootElement.elements();
		assertEquals("Incorrect number of children", 6, children.size());

		Element element = (Element) rootElement.selectSingleNode("option[text() = '12.34f']");
		assertNotNull("Option node should not be null", element);
		assertEquals("12.34 node not selected", "selected", element.attribute("selected").getValue());
		assertNull("No id rendered", element.attribute("id"));

		element = (Element) rootElement.selectSingleNode("option[text() = '12.35f']");
		assertNotNull("Option node should not be null", element);
		assertNull("12.35 node incorrectly selected", element.attribute("selected"));
		assertNull("No id rendered", element.attribute("id"));
	}
