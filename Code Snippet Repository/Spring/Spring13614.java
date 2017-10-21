	@Test
	public void withListAndTransformTagAndEditor() throws Exception {
		this.tag.setPath("realCountry");
		this.tag.setItems(Country.getCountries());
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

		TransformTag transformTag = new TransformTag();
		transformTag.setValue(Country.getCountries().get(0));
		transformTag.setVar("key");
		transformTag.setParent(this.tag);
		transformTag.setPageContext(getPageContext());
		transformTag.doStartTag();
		assertEquals("Austria", getPageContext().findAttribute("key"));
	}
