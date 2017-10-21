	@Test
	public void withListAndEditorAndNullValue() throws Exception {
		this.tag.setPath("realCountry");
		this.tag.setItems(Country.getCountries());
		this.tag.setItemValue("isoCode");
		this.tag.setItemLabel("name");
		TestBeanWithRealCountry testBean = (TestBeanWithRealCountry) getTestBean();
		testBean.setRealCountry(null);
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(testBean, "testBean");
		bindingResult.getPropertyAccessor().registerCustomEditor(Country.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(Country.getCountryWithIsoCode(text));
			}
			@Override
			public String getAsText() {
				Country value = (Country) getValue();
				if (value==null) {
					return "";
				}
				return value.getName();
			}
		});
		getPageContext().getRequest().setAttribute(BindingResult.MODEL_KEY_PREFIX + "testBean", bindingResult);
		this.tag.doStartTag();
		String output = getOutput();
		assertTrue(output.startsWith("<select "));
		assertTrue(output.endsWith("</select>"));
		assertFalse(output.contains("selected=\"selected\""));
	}
