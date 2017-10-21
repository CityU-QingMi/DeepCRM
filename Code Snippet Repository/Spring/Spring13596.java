	@Test
	public void withElementFormatter() throws Exception {
		this.bean.setRealCountry(Country.COUNTRY_UK);

		BeanPropertyBindingResult errors = new BeanPropertyBindingResult(this.bean, COMMAND_NAME);
		FormattingConversionService cs = new FormattingConversionService();
		cs.addFormatterForFieldType(Country.class, new Formatter<Country>() {
			@Override
			public String print(Country object, Locale locale) {
				return object.getName();
			}
			@Override
			public Country parse(String text, Locale locale) throws ParseException {
				return new Country(text, text);
			}
		});
		errors.initConversion(cs);
		exposeBindingResult(errors);

		this.tag.setPath("realCountry");
		this.tag.setItems(Country.getCountries());
		this.tag.setItemValue("isoCode");
		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);

		String output = getOutput();
		output = "<doc>" + output + "</doc>";

		SAXReader reader = new SAXReader();
		Document document = reader.read(new StringReader(output));
		Element rootElement = document.getRootElement();
		assertEquals(1, rootElement.elements().size());

		Element selectElement = rootElement.element("select");
		assertEquals("select", selectElement.getName());
		assertEquals("realCountry", selectElement.attribute("name").getValue());

		List children = selectElement.elements();
		assertEquals("Incorrect number of children", 4, children.size());

		Element e = (Element) selectElement.selectSingleNode("option[@value = 'UK']");
		assertEquals("UK node not selected", "selected", e.attribute("selected").getValue());
		assertEquals("United Kingdom", e.getText());
	}
