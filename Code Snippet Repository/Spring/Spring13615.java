	@Test
	public void withListAndEditor() throws Exception {
		this.tag.setPath("realCountry");
		this.tag.setItems(Country.getCountries());
		this.tag.setItemValue("isoCode");
		this.tag.setItemLabel("name");
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(getTestBean(), "testBean");
		bindingResult.getPropertyAccessor().registerCustomEditor(Country.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(Country.getCountryWithIsoCode(text));
			}
			@Override
			public String getAsText() {
				return ((Country) getValue()).getName();
			}
		});
		getPageContext().getRequest().setAttribute(BindingResult.MODEL_KEY_PREFIX + "testBean", bindingResult);
		this.tag.doStartTag();
		String output = getOutput();
		assertTrue(output.startsWith("<select "));
		assertTrue(output.endsWith("</select>"));
		assertTrue(output.contains("option value=\"AT\" selected=\"selected\">Austria"));
	}
