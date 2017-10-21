	@Test
	public void nestedPathWithListAndEditorAndNullValue() throws Exception {
		this.tag.setPath("bean.realCountry");
		this.tag.setItems(Country.getCountries());
		this.tag.setItemValue("isoCode");
		this.tag.setItemLabel("name");
		this.tag.setMultiple("false");
		TestBeanWrapper testBean = new TestBeanWrapper();
		TestBeanWithRealCountry withCountry = (TestBeanWithRealCountry) getTestBean();
		withCountry.setRealCountry(null);
		testBean.setBean(withCountry);
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(testBean , "testBean");
		bindingResult.getPropertyAccessor().registerCustomEditor(Country.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				if (text==null || text.length()==0) {
					setValue(null);
					return;
				}
				setValue(Country.getCountryWithIsoCode(text));
			}
			@Override
			public String getAsText() {
				Country value = (Country) getValue();
				if (value==null) {
					return null;
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
		assertFalse(output.contains("multiple=\"multiple\""));
	}
